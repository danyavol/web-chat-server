package com.danvol.webchat.controller;

import com.danvol.webchat.service.ChatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/msg")
@CrossOrigin
public class MessagesController {
    @Autowired private ChatsService chatsService;

    // Отправить сообщение
    @PostMapping("/new")
    public ResponseEntity newMessage(@RequestParam String uuid, @RequestParam String chatId, @RequestParam String message) {
        // input: uuid, chatId, message
        // output: msgId
        return chatsService.newMessage(uuid, chatId, message);
    }

    // Удалить сообщение
    @DeleteMapping("/delete")
    public ResponseEntity deleteMessage(@RequestParam String uuid, @RequestParam String chatId, @RequestParam int messageId) {
        // input: uuid, chatId, msgId
        // output:
        return chatsService.deleteMessage(uuid, chatId, messageId);
    }
}
