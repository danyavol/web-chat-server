package com.danvol.webchat.service;

import com.danvol.webchat.exception.ValidationException;
import com.danvol.webchat.mongo.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UsersService {

    User createUser(User userData);

    User authUser(User userData);

    User editUser(User userData);

    User deleteUser(User userData);

    User getUser(User userData);

    User findUsers(User userData);
}
