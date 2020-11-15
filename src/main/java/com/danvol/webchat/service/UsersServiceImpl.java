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
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
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
        User user = usersRepository.findByLogin(login);
        if (user == null) {
            // Неверный логин
            return new ResponseEntity<>(new RequestException("Неверный логин"), HttpStatus.OK);
        }
        if (!user.getPassword().equals(password)) {
            // Неверный пароль
            return new ResponseEntity<>(new RequestException("Неверный пароль"), HttpStatus.OK);
        } else {
            // Вход выполнен успешно
            UserDto userB = new UserDto(user, "authUser");
            return new ResponseEntity<>(userB, HttpStatus.OK);
        }
    }

    @Override
    public ResponseEntity checkUserAuth(String uuid) {
        User user = usersRepository.findByUuid(uuid);
        if (user != null) return new ResponseEntity<>(new UserDto(user, "authUser"), HttpStatus.OK);
        else return new ResponseEntity<>(new RequestException("Неверный UUID"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity editUser(String uuid, String login, String password, String name, String colorScheme) {
        User user = usersRepository.findByUuid(uuid);
        if (user == null) {
            return new ResponseEntity<>(new RequestException("Неверный UUID"), HttpStatus.OK);
        }

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

        // Проверка цветовой схемы
        if (colorScheme != null) {
            try {
                int colorSchemeInt = Integer.parseInt(colorScheme);
                if (colorSchemeInt >= 1 && colorSchemeInt <= 8) {
                    user.setColorScheme(colorSchemeInt);
                } else throw new Exception();
            } catch (Exception e) {
                return new ResponseEntity<>(new RequestException("Неверный формат цветовой схемы"), HttpStatus.OK);
            }
        }


        return new ResponseEntity<>(new UserDto(usersRepository.save(user), "editUser"), HttpStatus.OK);
    }


    @Override
    public ResponseEntity deleteUser(String uuid) {
        if (usersRepository.findByUuid(uuid) == null) {
            return new ResponseEntity<>(new RequestException("Пользователь не найден"), HttpStatus.OK);
        }
        usersRepository.deleteByUuid(uuid);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity getUser(String userId) {
        User user = usersRepository.findByUserId(userId);
        if (user == null) {
            return new ResponseEntity<>(new RequestException("Пользователь не найден"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new UserDto(user, "getUser"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity findUsers(String text) {
        if (text != null) text = text.toLowerCase();
        List<User> users = usersRepository.findAll();
        List<UserDto> usersDto = new ArrayList<>();

        for (int i = 0; i < users.size(); i++) {

            if (text != null) {
                // Поиск по логину
                if (users.get(i).getLogin().toLowerCase().contains(text) ) {
                    usersDto.add( new UserDto(users.get(i), "findUsers") );
                    continue;
                }
                // Поиск по имени
                if (users.get(i).getName().toLowerCase().contains(text) ) {
                    usersDto.add( new UserDto(users.get(i), "findUsers") );
                    continue;
                }
            } else {
                // Возвращает всех пользователей
                usersDto.add( new UserDto(users.get(i), "findUsers") );
            }

        }

        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }
}
