package com.danvol.webchat.session;

import com.danvol.webchat.mongo.entity.Message;
import com.danvol.webchat.mongo.entity.Notification;
import com.danvol.webchat.mongo.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UserSession {
    private String uuid;
    private List<Notification> notifications;

    public UserSession(User user) {
        this.uuid = user.getUuid();
        this.notifications = user.getNotifications();
    }
}
