package com.danvol.webchat.service;

import com.danvol.webchat.mongo.entity.User;
import org.springframework.http.ResponseEntity;


public interface UsersService {

    ResponseEntity createUser(User userData);

    ResponseEntity authUser(String login, String password);

    ResponseEntity editUser(User userData);

    ResponseEntity deleteUser(User userData);

    ResponseEntity getUser(User userData);

    ResponseEntity findUsers(User userData);
}
