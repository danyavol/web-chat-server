package com.danvol.webchat.mongo.repository;

import com.danvol.webchat.mongo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<User, Integer> {

}
