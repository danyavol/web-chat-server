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
    private String uuid;
    private String name;
    private String login;
    private int colorScheme;

    public UserDto(User obj, String type) {
        if (type.equals("authUser") || type.equals("createUser") || type.equals("editUser")) {
            this.userId = obj.getUserId();
            this.uuid = obj.getUuid();
            this.name = obj.getName();
            this.login = obj.getLogin();
            this.colorScheme = obj.getColorScheme();
        } else if (type.equals("getUser") || type.equals("findUsers")) {
            this.userId = obj.getUserId();
            this.name = obj.getName();
            this.login = obj.getLogin();
            this.colorScheme = obj.getColorScheme();
        }
    }
}
