package com.danvol.webchat.dto;

import com.danvol.webchat.mongo.entity.User;
import lombok.Data;

@Data
public class ChatUserDto {
    private String userId;
    private String login;
    private String name;
    private int colorScheme;

    public ChatUserDto(User user) {
        this.userId = user.getUserId();
        this.login = user.getLogin();
        this.name = user.getName();
        this.colorScheme = user.getColorScheme();
    }
}
