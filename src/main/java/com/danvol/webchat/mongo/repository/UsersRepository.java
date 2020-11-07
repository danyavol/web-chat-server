package com.danvol.webchat.mongo.repository;

import com.danvol.webchat.mongo.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends MongoRepository<User, String> {
    User findByLogin(String login);

    User findByUserId(String userId);

    void deleteByUserId(String userId);
}
