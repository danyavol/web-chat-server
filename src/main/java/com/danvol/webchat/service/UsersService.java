package com.danvol.webchat.service;

import com.danvol.webchat.exception.ValidationException;
import com.danvol.webchat.mongo.entity.User;

public interface UsersService {

    User saveUser(User userData) throws ValidationException;
}
