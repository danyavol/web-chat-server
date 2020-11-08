package com.danvol.webchat.service;

import com.danvol.webchat.mongo.entity.Chat;
import org.springframework.http.ResponseEntity;


public interface ChatsService {

    ResponseEntity createChat(String userId, String login);

//    ResponseEntity deleteChat(Chat chatData);

    ResponseEntity getAllChats(String userId);

    ResponseEntity getChatMessages(String userId, String chatId);

    ResponseEntity deleteMessage(String userId, String chatId, int messageId);

    ResponseEntity newMessage(String userId, String chatId, String message);
}
