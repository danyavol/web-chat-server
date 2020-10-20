package com.danvol.webchat.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;

@Entity
@Document(collection = "users")
@Data
public class User {
    @Id private Integer id;
    private String name;
    private String login;
    private String password;

}
