package com.danvol.webchat.controller;

import com.danvol.webchat.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    private UsersService usersService;


    // Создать нового пользователя
    @PostMapping("/new")
    public ResponseEntity createUser(@RequestParam String login, @RequestParam String password, @RequestParam String  name) {
        // input: login, password, name
        // output: userId, login, name
        return usersService.createUser(login, password, name);
    }

    // Авторизация пользователя
    @PostMapping("/auth")
    public ResponseEntity authUser(@RequestParam String login, @RequestParam String password) {
        // input: login, password
        // output: userId, login, name
        return usersService.authUser(login, password);
    }

    // Изменить существующего пользователя
    @PutMapping("/edit")
    public ResponseEntity editUser(@RequestParam String userId,
                                   @RequestParam(required = false) String login,
                                   @RequestParam(required = false) String password,
                                   @RequestParam(required = false) String name) {
        // input: userId
        // output: userId, login, name
        return usersService.editUser(userId, login, password, name);
    }

    // Изменить существующего пользователя
    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestParam String userId) {
        // input: userId
        // output:
        return usersService.deleteUser(userId);
    }

    // Вернуть данные одного пользователя
    @GetMapping("/get")
    public ResponseEntity getUser(@RequestParam String login) {
        // input: login
        // output: name
        return usersService.getUser(login);
    }

    // Найти пользователей
    @GetMapping("/find")
    public ResponseEntity findUsers(@RequestParam(required = false) String text) {
        // input: login, name or nothing
        // output: [{login, name}, ...] or []
        return usersService.findUsers(text);
    }

}
