package com.danvol.webchat.dto;

import com.danvol.webchat.mongo.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Entity;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {
    private String userId;
    private String name;
    private String login;
    private String password;

    public UserDto(User obj, String type) {
        if (type == "authUser") {
            this.userId = obj.getUserId();
            this.name = obj.getName();
            this.login = obj.getLogin();
        }
    }
}