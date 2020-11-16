package com.danvol.webchat.mongo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Notification {
    private String chatId;
    private List<Message> messages;

    public Notification(String chatId, List<Message> messages) {
        this.chatId = chatId;
        this.messages = messages;
    }

    public Notification(String chatId, Message message) {
        this.chatId = chatId;
        if (this.messages == null) {
            this.messages = new ArrayList<>();
        }
        this.messages.add(message);
    }
}
