package com.appdeveloperblog.sendwords.api;

 

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
 

@SpringBootApplication
@EnableConfigServer
public class SendWordsAppServerConfigApplication {
	 
	public static void main(String[] args) {
		SpringApplication.run(SendWordsAppServerConfigApplication.class, args);
		
		 
	}

}
