package com.danvol.webchat.service;

import com.danvol.webchat.mongo.entity.Chat;
import org.springframework.stereotype.Service;

@Service
public interface ChatsService {

    Chat createChat(Chat chatData);

    Chat deleteChat(Chat chatData);

    Chat getAllChats(Chat chatData);

    Chat getChatMessages(Chat chatData);

    Chat deleteMessage(Chat chatData);

    Chat newMessage(Chat chatData);
}
