package com.danvol.webchat.mongo.repository;

import com.danvol.webchat.mongo.entity.Chat;
import com.danvol.webchat.mongo.entity.ChatUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatsRepository extends MongoRepository<Chat, String> {
    Chat findByUsers(List<ChatUser> chatUsers);

    Chat findByChatId(String chatId);

}
