package com.sendwords.api.client.utils;

import org.apache.juli.logging.Log;

import org.apache.juli.logging.LogFactory;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.config.TopicBuilder;

 

public class FirstTimeInitializer implements CommandLineRunner {
	@Value("${sendClient.kafka.producer.topic}")
	private String topicProducer;
	@Value("${sendClient.kafka.consumer.topic}")
	private String topicConsumer;
	private final Log logger=LogFactory.getLog(FirstTimeInitializer.class);

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		producer();
		consumer();
	}
	 
	  public NewTopic producer() {
	    return TopicBuilder.name(topicProducer)
	      .partitions(6)
	      .replicas(3)
	      .build();
	  } 
	  public NewTopic consumer() {
		    return TopicBuilder.name(topicConsumer)
		      .partitions(6)
		      .replicas(3)
		      .build();
		  }  
    
     
}
