package com.danvol.webchat.controller;

import com.danvol.webchat.service.ChatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chats")
@CrossOrigin
public class ChatsController {
    @Autowired
    private ChatsService chatsService;

    // Создать новый чат
    @PostMapping("/new")
    public ResponseEntity createChat(@RequestParam(name = "myId") String userId, @RequestParam(name = "mateLogin") String login) {
        // input: {creator: userId, chatMember: login}
        // validation: login is existing
        // output: chatId
        return chatsService.createChat(userId, login);
    }

//    // Удалить чат
//    @DeleteMapping("/delete")
//    public Chat deleteChat(@RequestBody Chat chatData) {
//        // input: userId, chatId
//        // output:
//        return chatsService.deleteChat(chatData);
//    }
//
//    // Получить список чатов
//    @GetMapping("/getAll")
//    public Chat getAllChats(@RequestBody Chat chatData) {
//        // input: userId
//        // output: [ {chatId, login} ]
//        return chatsService.getAllChats(chatData);
//    }
//
//    // Получить список сообщений в чате
//    @GetMapping("/getOne")
//    public Chat getChatMessages(@RequestBody Chat chatData) {
//        // input: userId, chatId
//        // output:
//        return chatsService.getChatMessages(chatData);
//    }
}
