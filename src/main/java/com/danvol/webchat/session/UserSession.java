package com.danvol.webchat.session;

import com.danvol.webchat.dto.ChatDto;
import com.danvol.webchat.mongo.entity.Notification;
import com.danvol.webchat.mongo.entity.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UserSession implements Cloneable {
    private String uuid;
    private List<ChatDto> newChats;
    private List<Notification> notifications;

    public UserSession(User user) {
        this.uuid = user.getUuid();
        this.newChats = new ArrayList<>();
        this.notifications = user.getNotifications();
    }

    public UserSession(User user, List<ChatDto> newChats) {
        this.uuid = user.getUuid();
        this.newChats = newChats;
        this.notifications = user.getNotifications();
    }

    public void addNewChat(ChatDto newChat) {
        this.newChats.add(newChat);
    }

    public void clearNewChats() {
        this.newChats = new ArrayList<>();
    }

    public UserSession clone() throws CloneNotSupportedException{

        return (UserSession) super.clone();
    }
}
