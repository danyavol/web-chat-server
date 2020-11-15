package com.danvol.webchat.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.util.UUID;

@Entity
@Document(collection = "users")
@Data
public class User {
    @Id private String userId;
    private String uuid;
    private String login;
    private String password;
    private String name;
    private int colorScheme;

    public User(String login, String password, String name) {
        this.uuid = UUID.randomUUID().toString();
        this.login = login;
        this.password = password;
        this.name = name;

        int max = 8;
        int min = 1;
        this.colorScheme = (int) (Math.random() * ((max - min) + 1)) + min;
    }
}
