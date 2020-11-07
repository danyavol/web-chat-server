package com.danvol.webchat.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data
public class Message {
    private int messageId;
    private String messageText;
    private String sender;
    private Date sendTime;

    public Message(String sender, String messageText) {
        this.sender = sender;
        this.messageText = messageText;
        this.sendTime = new Date();
    }
}
