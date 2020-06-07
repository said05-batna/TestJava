package com.sendwords.api.client.kafka;
import java.io.IOException;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.apache.kafka.clients.producer.ProducerRecord;

 


@Component
@Qualifier("KafkaProducerClient")
public class KafkaProducerClient {
	
	   public  void KafkaProducerClientsend(String brokers, String topicName,String words) throws IOException

	    {
 
	        Properties properties = new Properties(); 
	        properties.setProperty("bootstrap.servers", brokers); 
	        properties.setProperty("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
	        properties.setProperty("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
	        // producer acks
	        properties.setProperty("acks", "1");
	        properties.setProperty("retries", "3");
	        properties.setProperty("linger.ms", "1");

	        KafkaProducer<String, String> producer = new KafkaProducer<>(properties); 
	            try
	            {
	                producer.send(new ProducerRecord<String, String>(topicName, words)).get();
	            }
	            catch (Exception ex)
	            {
	                System.out.print(ex.getMessage());
	                throw new IOException(ex.toString());
	            }
	             
	        }
	    }
	
	
	 
 

