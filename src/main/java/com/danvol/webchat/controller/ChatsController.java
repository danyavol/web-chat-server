package com.danvol.webchat.controller;

import com.danvol.webchat.mongo.entity.Chat;
import com.danvol.webchat.service.ChatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chats")
public class ChatsController {
    @Autowired private ChatsService chatsService;

    // Создать новый чат
    @PostMapping("/new")
    public Chat createChat(@RequestBody Chat chatData) {
        // input: {creator: userId, chatMember: login}
        // validation: login is existing
        // output: chatId
        return chatsService.createChat(chatData);
    }

    // Удалить чат
    @DeleteMapping("/delete")
    public Chat deleteChat(@RequestBody Chat chatData) {
        // input: userId, chatId
        // output:
        return chatsService.deleteChat(chatData);
    }

    // Получить список чатов
    @GetMapping("/getAll")
    public Chat getAllChats(@RequestBody Chat chatData) {
        // input: userId
        // output: [ {chatId, login} ]
        return chatsService.getAllChats(chatData);
    }

    // Получить список сообщений в чате
    @GetMapping("/getOne")
    public Chat getChatMessages(@RequestBody Chat chatData) {
        // input: userId, chatId
        // output:
        return chatsService.getChatMessages(chatData);
    }
}
