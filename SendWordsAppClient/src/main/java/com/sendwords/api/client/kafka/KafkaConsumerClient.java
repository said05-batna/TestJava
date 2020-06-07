package com.sendwords.api.client.kafka;

import java.util.Arrays;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.KafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
 
import org.springframework.stereotype.Component;
@Component
@Configuration
@EnableKafka
public class KafkaConsumerClient {
	@Value("${sendClient.kafka.boot.server}")
	private String kafkaServer;

	@Value("${sendClient.kafka.consumer.groupId}")
	private String kafkaGroupId;
	 
	 
	
	@Bean
	public ConsumerFactory<String, String> consumerConfig() {
		// TODO Auto-generated method stub
		Map<String, Object> config = new HashMap<>();
		config.put("bootstrap.servers", kafkaServer);
		config.put("group.id", kafkaGroupId);
		config.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		config.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
		config.put("auto.offset.reset","earliest");
		config.put("max.poll.interval.ms",600000);
		return new DefaultKafkaConsumerFactory<>(config);
	}

	@Bean
	public KafkaListenerContainerFactory<ConcurrentMessageListenerContainer<String, String>> kafkaListenerContainerFactory() {
		ConcurrentKafkaListenerContainerFactory<String, String> listener = new ConcurrentKafkaListenerContainerFactory<>();
		listener.setConsumerFactory(consumerConfig());
		return listener;
	}
}
