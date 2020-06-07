package com.sendwords.api.processor.kafka;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Services {
	private KafkaConsumers kafkaConsumers;
@Autowired
	public Services(KafkaConsumers kafkaConsumers) throws InterruptedException, IOException {
		super();
		this.kafkaConsumers = kafkaConsumers;
		kafkaConsumers.receive();
	}
	
	

}
