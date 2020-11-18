package com.danvol.webchat.mongo.entity;

import lombok.Data;

import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Document(collection = "users")
@NoArgsConstructor
@Data
public class User {
    @Id private String userId;
    private String uuid;
    private String login;
    private String password;
    private String name;
    private int colorScheme;
    private List<Notification> notifications;

    public User(String login, String password, String name) {
        this.uuid = UUID.randomUUID().toString();
        this.login = login;
        this.password = password;
        this.name = name;
        this.notifications = new ArrayList<>();

        int max = 8;
        int min = 1;
        this.colorScheme = (int) (Math.random() * ((max - min) + 1)) + min;
    }

    public void addNewMsg(String chatId, Message message) {
        for (int i = 0; i < this.notifications.size(); i++) {
            if (this.notifications.get(i).getChatId().equals( chatId )) {
                // Чат существует, добавляем туда новое сообщение
                this.notifications.get(i).getNewMsg().add(new NotificationMessage(message));
                return;
            }
        }
        // Чат не существует, создаем новый
        this.notifications.add(new Notification(chatId, message, "new"));
    }

    public void addDeletedMsg(String chatId, Message message) {
        for (int i = 0; i < this.notifications.size(); i++) {
            if (this.notifications.get(i).getChatId().equals( chatId )) {
                // Чат существует, добавляем туда удаленное сообщение
                this.notifications.get(i).getDeletedMsg().add(new NotificationMessage(message));
                return;
            }
        }
        // Чат не существует, создаем новый
        this.notifications.add(new Notification(chatId, message, "deleted"));
    }

    public void clearNotifications(String chatId) {
        for (int i = 0; i < this.notifications.size(); i++) {
            if (this.notifications.get(i).getChatId().equals( chatId )) {
                // Нужный чат найден, удаляем уведомления
                this.notifications.remove(i);
            }
        }
    }

}
