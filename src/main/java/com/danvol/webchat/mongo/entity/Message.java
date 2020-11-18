package com.danvol.webchat.mongo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@NoArgsConstructor
@Data
public class Message {
    private int messageId;
    private String messageText;
    private String senderId;
    private Date sendTime;

    public Message(String senderId, String messageText) {
        this.senderId = senderId;
        this.messageText = messageText;
        this.sendTime = new Date();
    }
}
