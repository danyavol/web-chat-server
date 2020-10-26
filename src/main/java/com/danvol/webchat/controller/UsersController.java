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

    // Save new user
    @PostMapping("/save")
    public User saveUser(@RequestBody User userData) throws ValidationException {
        return usersService.saveUser(userData);
    }

    // Update existing user
    @PostMapping("/update")
    public User updateUser(@RequestBody User userData) {
        return userData;
    }

    // Delete existing user
    @PostMapping("/delete")
    public User deleteUser(@RequestBody User userData) {
        return userData;
    }

}
