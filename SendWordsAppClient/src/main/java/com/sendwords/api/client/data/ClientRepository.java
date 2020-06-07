package com.sendwords.api.client.data;

import java.util.List;
import java.util.UUID;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


 
public interface ClientRepository   extends MongoRepository<Message,String>{
	List<Message> findByWordsLike(String words);
}
