package com.danvol.webchat.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
@Document(collection = "users")
@Data
public class User {
    @Id private String userId;
    private String login;
    private String password;
    private String name;

    public User(String login, String password, String name) {
        this.login = login;
        this.password = password;
        this.name = name;
    }
}
