package com.danvol.webchat.service;

import org.springframework.http.ResponseEntity;


public interface UsersService {

    ResponseEntity createUser(String login, String password, String name);

    ResponseEntity authUser(String login, String password);

    ResponseEntity checkUserAuth(String uuid);

    ResponseEntity editUser(String uuid, String login, String password, String name, String colorScheme);

    ResponseEntity deleteUser(String uuid);

    ResponseEntity getUser(String userId);

    ResponseEntity findUsers(String text);
}
