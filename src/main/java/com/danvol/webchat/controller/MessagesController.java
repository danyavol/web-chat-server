package com.danvol.webchat.controller;

import com.danvol.webchat.mongo.entity.Chat;
import com.danvol.webchat.service.ChatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/msg")
public class MessagesController {
    @Autowired private ChatsService chatsService;

    // Отправить сообщение
    @PostMapping("/new")
    public ResponseEntity newMessage(@RequestParam String userId, @RequestParam String chatId, @RequestParam String message) {
        // input: userId, chatId, message
        // output: msgId
        return chatsService.newMessage(userId, chatId, message);
    }

    // Удалить сообщение
    @DeleteMapping("/delete")
    public ResponseEntity deleteMessage(@RequestParam String userId, @RequestParam String chatId, @RequestParam int messageId) {
        // input: userId, chatId, msgId
        // output:
        return chatsService.deleteMessage(userId, chatId, messageId);
    }
}
