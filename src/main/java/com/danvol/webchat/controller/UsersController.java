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


    // Авторизация пользователя
    @PostMapping("/auth")
    public ResponseEntity authUser(@RequestParam String login, @RequestParam String password) {
        // input: login, password
        // output: userId
        return usersService.authUser(login, password);
    }

//    // Создать нового пользователя
//    @PostMapping("/new")
//    public ResponseEntity createUser(@RequestBody User userData) throws ValidationException {
//        // input: login, password, name
//        // validation: login is unique
//        // output: userId
//        return usersService.createUser(userData);
//    }



//    // Изменить существующего пользователя
//    @PutMapping("/edit")
//    public ResponseEntity editUser(@RequestBody User userData) {
//        // input: userId
//        // output:
//        return usersService.editUser(userData);
//    }
//
//    // Изменить существующего пользователя
//    @DeleteMapping("/delete")
//    public ResponseEntity deleteUser(@RequestBody User userData) {
//        // input: userId
//        // output:
//        return usersService.deleteUser(userData);
//    }
//
//    // Вернуть данные одного пользователя
//    @GetMapping("/get")
//    public ResponseEntity getUser(@RequestBody User userData) {
//        // input: login
//        // output: name
//        return usersService.getUser(userData);
//    }
//
//    // Найти пользователей
//    @GetMapping("/find")
//    public ResponseEntity findUsers(@RequestBody User userData) {
//        // input: login, name or nothing
//        // output: [{login, name}, ...] or []
//        return usersService.findUsers(userData);
//    }

}
