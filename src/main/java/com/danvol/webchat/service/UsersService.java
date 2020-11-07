package com.danvol.webchat.service;

import com.danvol.webchat.mongo.entity.User;
import org.springframework.http.ResponseEntity;


public interface UsersService {

    ResponseEntity createUser(String login, String password, String name);

    ResponseEntity authUser(String login, String password);

    ResponseEntity editUser(String userId, String login, String password, String name);

    ResponseEntity deleteUser(String userId);

    ResponseEntity getUser(String login);

    ResponseEntity findUsers(String text);
}
