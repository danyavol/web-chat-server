//package com.danvol.webchat.service;
//
//import com.danvol.webchat.exception.ValidationException;
//import com.danvol.webchat.mongo.entity.User;
//import com.danvol.webchat.mongo.repository.UsersRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import static java.util.Objects.isNull;
//
//
//@Service
//public class DefaultUsersService implements UsersService {
//    @Autowired private UsersRepository usersRepository;
//
//
//    @Override
//    public User saveUser(User userData) throws ValidationException {
//
//        validateUser(userData);
//
//        return usersRepository.save(userData);
//    }
//
//    private void validateUser(User userData) throws ValidationException {
//        if (isNull(userData)) {
//            throw new ValidationException("Object user is null");
//        }
//        if (isNull(userData.getLogin()) || userData.getLogin().isEmpty()) {
//            throw new ValidationException("Login is empty");
//        }
//    }
//}
