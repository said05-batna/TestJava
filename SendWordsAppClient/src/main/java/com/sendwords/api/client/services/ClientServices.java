package com.sendwords.api.client.services;

import java.io.IOException;
import java.util.List;

import com.sendwords.api.client.data.Message;

public interface ClientServices  {

 
	String clientsendMessageToTopic(String message) throws IOException;
	List<Message> getMessagesBywords(String word);
	List<Message> getAllMessages();
	
	
}
