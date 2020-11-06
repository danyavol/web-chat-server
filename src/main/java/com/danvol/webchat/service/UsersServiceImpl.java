package com.danvol.webchat.service;

import com.danvol.webchat.exception.RequestException;
import com.danvol.webchat.mongo.entity.User;
import com.danvol.webchat.dto.UserDto;
import com.danvol.webchat.mongo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public ResponseEntity authUser(String login, String password) {
        User userA = usersRepository.findByLogin(login);
        if (userA == null) {
            // Неверный логин
            return new ResponseEntity<>(new RequestException("Неверный логин"), HttpStatus.OK);
        }
        if (!userA.getPassword().equals(password)) {
            // Неверный пароль
            return new ResponseEntity<>(new RequestException("Неверный пароль"), HttpStatus.OK);
        } else {
            // Вход выполнен успешно
            UserDto userB = new UserDto(userA, "authUser");
            return new ResponseEntity<>(userB, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity createUser(User userData) {
        return null;
    }

    @Override
    public ResponseEntity editUser(User userData) {
        return null;
    }

    @Override
    public ResponseEntity deleteUser(User userData) {
        return null;
    }

    @Override
    public ResponseEntity getUser(User userData) {
        return null;
    }

    @Override
    public ResponseEntity findUsers(User userData) {
        return null;
    }
}
