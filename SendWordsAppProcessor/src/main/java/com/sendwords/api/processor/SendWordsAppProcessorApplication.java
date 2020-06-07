package com.sendwords.api.processor;

import org.springframework.boot.SpringApplication;


 
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com")
public class SendWordsAppProcessorApplication {

	public static void main(String[] args) {
		SpringApplication.run(SendWordsAppProcessorApplication.class, args);
	 
	}
	/*@Bean
	@Autowired
    public ApplicationRunner runner(KafkaConsumers kafkaConsumers) {
		return (args) -> {
			kafkaConsumers.receive();
       
    };

}*/
}
