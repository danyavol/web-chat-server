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
    public ResponseEntity createChat(@RequestParam String myUuid, @RequestParam String mateId) {
        // input: myUuid, mateId
        // output: chat
        return chatsService.createChat(myUuid, mateId);
    }

//    // Удалить чат
//    @DeleteMapping("/delete")
//    public Chat deleteChat(@RequestBody Chat chatData) {
//        // input: userId, chatId
//        // output:
//        return chatsService.deleteChat(chatData);
//    }

    // Получить список чатов
    @GetMapping("/getAll")
    public ResponseEntity getAllChats(@RequestParam String uuid) {
        // input: uuid
        // output: [ chat ]
        return chatsService.getAllChats(uuid);
    }

    // Получить список сообщений в чате
    @GetMapping("/getOne")
    public ResponseEntity getChatMessages(@RequestParam String uuid, @RequestParam String chatId) {
        // input: uuid, chatId
        // output:
        return chatsService.getChatMessages(uuid, chatId);
    }
}
