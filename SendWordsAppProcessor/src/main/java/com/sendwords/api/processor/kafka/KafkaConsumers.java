package com.sendwords.api.processor.kafka;



import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Component;

@Component
public class KafkaConsumers {
	private KafkaProducers kafkaProducers;
	@Value("${processor.kafka.boot.server}")
	private String kafkaServer;

	@Value("${processor.kafka.consumer.groupId}")
	private String kafkaGroupId;
	@Value("${processor.kafka.consumer.topic}")
	private String topic;
	
	@Autowired
    public KafkaConsumers(KafkaProducers kafkaProducers) {
     this.kafkaProducers=kafkaProducers;
    }
 
  

    public void receive() throws InterruptedException, IOException {
    	//kafka configuration properties
    	Properties props = new Properties();
        props.put("bootstrap.servers", kafkaServer);
        props.put("group.id", kafkaGroupId);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", 1000);
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("topic",topic);
        props.put("linger.ms",60000);
        props.put("max.poll.interval.ms",60000);
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(props.getProperty("topic")));
         
         
        try {
            while(true) {
            	
                ConsumerRecords<String, String> records = consumer.poll(Long.MAX_VALUE);
                for (TopicPartition partition : records.partitions()) {
                    List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
                    String messageString="";
                    for (ConsumerRecord<String, String> record : partitionRecords) {
                    	messageString  = messageString+" "+record.value();
                    }
                    System.out.println("******* recuperer procesor "+messageString);
                    kafkaProducers.send(messageString);
                    long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                    consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
                }
                //sleep thread one minute after that catch message from topic
              Thread.sleep(60000);
            }
        } finally {
          consumer.close();
        }
    	
     
}
}
