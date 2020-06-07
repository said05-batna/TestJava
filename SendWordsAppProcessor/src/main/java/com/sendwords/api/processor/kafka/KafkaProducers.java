package com.sendwords.api.processor.kafka;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducers {
	@Value("${processor.kafka.boot.server}")
	private String kafkaServer; 
	@Value("${processor.kafka.producer.topic}")
	private String topic;
public void send(String words) throws IOException {
	  Properties properties = new Properties(); 
      properties.setProperty("bootstrap.servers", kafkaServer); 
      properties.setProperty("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
      properties.setProperty("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
      // producer acks
      properties.setProperty("acks", "1");
      properties.setProperty("retries", "3");
      properties.setProperty("linger.ms", "1");

      KafkaProducer<String, String> producer = new KafkaProducer<>(properties); 
          try
          {
              producer.send(new ProducerRecord<String, String>(topic, words)).get();
          }
          catch (Exception ex)
          {
              System.out.print(ex.getMessage());
              throw new IOException(ex.toString());
          }
           
      }
}
        
