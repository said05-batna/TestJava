package com.sendwords.api.client.ui.controllers;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


import com.sendwords.api.client.data.Message;
import com.sendwords.api.client.services.ClientServices;

@RestController
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private Environment environment;
	@Autowired
	ClientServices clientServices;
	 
	@PostMapping(value = "/createMessage",
			consumes = {MediaType.ALL_VALUE,MediaType.APPLICATION_JSON_VALUE},
			produces={MediaType.ALL_VALUE,MediaType.APPLICATION_JSON_VALUE}
			)

	public ResponseEntity<String> createUser(@Valid @RequestBody String word) throws IOException {
        String message=clientServices.clientsendMessageToTopic(word);
        return new ResponseEntity(message,HttpStatus.CREATED);
	} 
	
	@GetMapping(value="/find/{word}",produces={MediaType.ALL_VALUE,MediaType.APPLICATION_JSON_VALUE})
	   public  ResponseEntity<List<Message>> findbyword(@PathVariable String word){  
		List<Message> result =clientServices.getMessagesBywords(word);
	        return new ResponseEntity< List<Message>>(result,HttpStatus.OK);
	   }
	
	@GetMapping(value="/find",produces={MediaType.ALL_VALUE,MediaType.APPLICATION_JSON_VALUE})
	   public  ResponseEntity<List<Message>> findAll(){  
		List<Message> result =clientServices.getAllMessages();
	        return new ResponseEntity< List<Message>>(result,HttpStatus.OK);
	   }
}
