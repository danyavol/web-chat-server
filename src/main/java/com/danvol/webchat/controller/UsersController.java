package com.danvol.webchat.controller;

import com.danvol.webchat.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UsersController {
    @Autowired
    private UsersService usersService;


    // Создать нового пользователя
    @PostMapping("/new")
    public ResponseEntity createUser(@RequestParam String login, @RequestParam String password, @RequestParam String  name) {
        // input: login, password, name
        // output: uuid, userId, login, name
        return usersService.createUser(login, password, name);
    }

    // Авторизация пользователя
    @PostMapping("/auth")
    public ResponseEntity authUser(@RequestParam String login, @RequestParam String password) {
        // input: login, password
        // output: uuid, userId, login, name
        return usersService.authUser(login, password);
    }

    // Авторизация пользователя
    @PostMapping("/checkAuth")
    public ResponseEntity checkUserAuth(@RequestParam String uuid) {
        // input: uuid
        // output: bool
        return usersService.checkUserAuth(uuid);
    }

    // Изменить существующего пользователя
    @PutMapping("/edit")
    public ResponseEntity editUser(@RequestParam String uuid,
                                   @RequestParam(required = false) String login,
                                   @RequestParam(required = false) String password,
                                   @RequestParam(required = false) String name,
                                   @RequestParam(required = false) String colorScheme) {
        // input: uuid
        // output: userId, login, name
        return usersService.editUser(uuid, login, password, name, colorScheme);
    }

    // Изменить существующего пользователя
    @DeleteMapping("/delete")
    public ResponseEntity deleteUser(@RequestParam String uuid) {
        // input: uuid
        // output:
        return usersService.deleteUser(uuid);
    }

    // Вернуть данные одного пользователя
    @GetMapping("/get")
    public ResponseEntity getUser(@RequestParam String userId) {
        // input: userId
        // output: name
        return usersService.getUser(userId);
    }

    // Найти пользователей
    @GetMapping("/find")
    public ResponseEntity findUsers(@RequestParam(required = false) String text) {
        // input: login, name or nothing
        // output: [{login, name}, ...] or []
        return usersService.findUsers(text);
    }

}
