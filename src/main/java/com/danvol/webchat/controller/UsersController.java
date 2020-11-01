package com.danvol.webchat.controller;

import com.danvol.webchat.exception.ValidationException;
import com.danvol.webchat.mongo.entity.User;
import com.danvol.webchat.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired private UsersService usersService;

    // Создать нового пользователя
    @PostMapping("/new")
    public User createUser(@RequestBody User userData) throws ValidationException {
        // input: login, password, name
        // validation: login is unique
        // output: userId
        return usersService.createUser(userData);
    }

    // Авторизация пользователя
    @PostMapping("/auth")
    public User authUser(@RequestBody User userData) {
        // input: login, password
        // output: userId
        return usersService.authUser(userData);
    }

    // Изменить существующего пользователя
    @PutMapping("/edit")
    public User editUser(@RequestBody User userData) {
        // input: userId
        // output:
        return usersService.editUser(userData);
    }

    // Изменить существующего пользователя
    @DeleteMapping("/delete")
    public User deleteUser(@RequestBody User userData) {
        // input: userId
        // output:
        return usersService.deleteUser(userData);
    }

    // Вернуть данные одного пользователя
    @GetMapping("/get")
    public User getUser(@RequestBody User userData) {
        // input: login
        // output: name
        return usersService.getUser(userData);
    }

    // Найти пользователей
    @GetMapping("/find")
    public User findUsers(@RequestBody User userData) {
        // input: login, name or nothing
        // output: [{login, name}, ...] or []
        return usersService.findUsers(userData);
    }

}
