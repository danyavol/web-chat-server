package com.danvol.webchat.service;

import com.danvol.webchat.exception.RequestException;
import com.danvol.webchat.mongo.entity.User;
import com.danvol.webchat.dto.UserDto;
import com.danvol.webchat.mongo.repository.UsersRepository;
import com.danvol.webchat.validation.UserValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersRepository usersRepository;


    @Override
    public ResponseEntity createUser(String login, String password, String name) {

        // Проверка существует ли такой же логин
        if (usersRepository.findByLogin(login) != null) {
            return new ResponseEntity<>(new RequestException("Пользователь с таким логином уже существует"), HttpStatus.OK);
        }

        // Проверка логина
        String validLogin = UserValidation.login(login);
        if (validLogin.length() == 0) {
            return new ResponseEntity<>(new RequestException("Неверный формат логина"), HttpStatus.OK);
        }

        // Проверка пароля
        String validPassword = UserValidation.password(password);
        if (validPassword.length() == 0) {
            return new ResponseEntity<>(new RequestException("Неверный формат пароля"), HttpStatus.OK);
        }

        // Проверка имени
        String validName = UserValidation.name(name);
        if (validName.length() == 0) {
            return new ResponseEntity<>(new RequestException("Неверный формат имени"), HttpStatus.OK);
        }

        // Валидация пройдена, сохраняем пользователя
        User user = usersRepository.save(new User(validLogin, validPassword, validName));
        return new ResponseEntity<>(new UserDto(user, "createUser"), HttpStatus.OK);
    }


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
    public ResponseEntity editUser(String userId, String login, String password, String name) {
        User user = usersRepository.findByUserId(userId);

        // Проверка логина
        if (login != null) {
            String validLogin = UserValidation.login(login);
            if (validLogin.length() != 0) user.setLogin(validLogin);
            else return new ResponseEntity<>(new RequestException("Неверный формат логина"), HttpStatus.OK);
        }

        // Проверка пароля
        if (password != null) {
            String validPassword = UserValidation.password(password);
            if (validPassword.length() != 0) user.setPassword(validPassword);
            else return new ResponseEntity<>(new RequestException("Неверный формат пароля"), HttpStatus.OK);
        }

        // Проверка имени
        if (name != null) {
            String validName = UserValidation.name(name);
            if (validName.length() != 0) user.setName(validName);
            else return new ResponseEntity<>(new RequestException("Неверный формат имени"), HttpStatus.OK);

        }

        return new ResponseEntity<>(new UserDto(usersRepository.save(user), "editUser"), HttpStatus.OK);
    }


    @Override
    public ResponseEntity deleteUser(String userId) {
        if (usersRepository.findByUserId(userId) == null) {
            return new ResponseEntity<>(new RequestException("Пользователь не найден"), HttpStatus.OK);
        }
        usersRepository.deleteByUserId(userId);
        return new ResponseEntity<>(HttpStatus.OK);
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
