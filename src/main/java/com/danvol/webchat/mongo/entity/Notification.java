package com.danvol.webchat.mongo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class Notification {
    private String chatId;
    private List<NotificationMessage> newMsg;
    private List<NotificationMessage> deletedMsg;

    public Notification(String chatId, Message message, String type) {
        this.chatId = chatId;
        if (this.newMsg == null) this.newMsg = new ArrayList<>();
        if (this.deletedMsg == null) this.deletedMsg = new ArrayList<>();

        if (type.equals("new")) {
            // Если добавляется в уведомление новое сообщение
            this.newMsg.add(new NotificationMessage(message));
        } else if (type.equals("deleted")) {
            // Если добавляется в уведомления удаленное сообщение
            this.deletedMsg.add(new NotificationMessage(message));
        }
    }

}
