package com.sendwords.api.client;

import org.springframework.boot.SpringApplication;



 
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableDiscoveryClient
@ComponentScan("com")
 
public class SendWordsAppClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SendWordsAppClientApplication.class, args);
	}

}
