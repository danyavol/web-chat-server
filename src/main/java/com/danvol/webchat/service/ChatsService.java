package com.danvol.webchat.service;

import org.springframework.http.ResponseEntity;


public interface ChatsService {

    ResponseEntity createChat(String myUuid, String mateId);

//    ResponseEntity deleteChat(Chat chatData);

    ResponseEntity getAllChats(String uuid);

    ResponseEntity getChatMessages(String uuid, String chatId);

    ResponseEntity newMessage(String uuid, String chatId, String message);

    ResponseEntity deleteMessage(String uuid, String chatId, int messageId);

    ResponseEntity checkNotifications(String uuid);

    ResponseEntity clearNotifications(String uuid, String chatId);
}
