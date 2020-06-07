package com.sendwords.api.client.services;

import java.io.IOException;

import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.kafka.annotation.KafkaListener;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.springframework.stereotype.Service;
import org.apache.kafka.clients.consumer.*;


import com.sendwords.api.client.data.ClientRepository;
import com.sendwords.api.client.data.Message;
import com.sendwords.api.client.kafka.KafkaConsumerClient;
import com.sendwords.api.client.kafka.KafkaProducerClient;
@Service


public class ClientServicesImplement implements ClientServices{
	@Autowired
	@Qualifier("KafkaProducerClient")
	private KafkaProducerClient kafkaProducerClient;
	@Autowired
	private KafkaConsumerClient kafkaConsumerClient;
	
	private ClientRepository clientRepository;
	
	private final MongoTemplate mongoTemplate ;
	
	
	
	@Autowired
	public ClientServicesImplement(ClientRepository clientRepository,MongoTemplate mongoTemplate) {
		super();
		this.clientRepository = clientRepository;
		this.mongoTemplate=mongoTemplate;
	}

	@Value("${sendClient.kafka.boot.server}")
	private String kafkaServer;
 
	@Value("${sendClient.kafka.producer.topic}")
	private String topic;
	@Override
	public List<Message> getMessagesBywords(String word) {

		List<Message> messsages =clientRepository.findByWordsLike(word);
		 
		return messsages;
	}

	@Override
	public String clientsendMessageToTopic(String message) throws IOException {
		kafkaProducerClient.KafkaProducerClientsend(kafkaServer, topic, message);
		return message;
	}
//@KafkaListener(topics = "test", groupId = "group_id")
	
	@KafkaListener(topics = "#{'${sendClient.kafka.consumer.topic}'.split(',')}", groupId = "#{T(java.util.UUID).randomUUID().toString()}")
 public void consume(String message) {		
		System.out.println("******* recuperer Client  "+message);
		ModelMapper modelmapper = new ModelMapper();		
		modelmapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		Message messages = modelmapper.map(message, Message.class);
		messages.setId(UUID.randomUUID().toString());
		messages.setWords(message);
        clientRepository.insert(messages);
        
    }
	
	

	public List<Message> getAllMessages() {
		
		return clientRepository.findAll();
		
	}

}
