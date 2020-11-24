package com.danvol.webchat.mongo.entity;

import com.mongodb.BasicDBObject;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class NotificationMessage {
    private int messageId;
    private String messageText;
    private BasicDBObject sender;
    private Date sendTime;

    public NotificationMessage(Message message) {
        this.messageId = message.getMessageId();
        this.messageText = message.getMessageText();
        this.sender = new BasicDBObject();
        this.sender.put("userId", message.getSenderId());
        this.sendTime = message.getSendTime();
    }
}
