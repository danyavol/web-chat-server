package com.danvol.webchat.controller;

import com.danvol.webchat.mongo.entity.Chat;
import com.danvol.webchat.service.ChatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/msg")
public class MessagesController {
//    @Autowired private ChatsService chatsService;
//
//    // Отправить сообщение
//    @PostMapping("/new")
//    public Chat newMessage(@RequestBody Chat chatData) {
//        // input: userId, chatId, msgText
//        // output: msgId
//        return chatsService.newMessage(chatData);
//    }
//
//    // Удалить сообщение
//    @DeleteMapping("/delete")
//    public Chat deleteMessage(@RequestBody Chat chatData) {
//        // input: userId, chatId, msgId
//        // output:
//        return chatsService.deleteMessage(chatData);
//    }
}
