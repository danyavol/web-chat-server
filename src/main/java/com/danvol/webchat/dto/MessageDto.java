package com.danvol.webchat.dto;

import com.danvol.webchat.mongo.entity.Message;
import com.danvol.webchat.mongo.entity.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MessageDto {
    private int messageId;
    private String messageText;
    private String senderName;
    private String senderLogin;
    private Date sendTime;

    public MessageDto(Message msg, List<User> users) {
        this.messageId = msg.getMessageId();
        this.messageText = msg.getMessageText();
        this.sendTime = msg.getSendTime();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(msg.getSender())) {
                this.senderName = users.get(i).getName();
                this.senderLogin = users.get(i).getLogin();
            }
        }

    }

    public MessageDto(Message msg, User user) {
        this.messageId = msg.getMessageId();
        this.messageText = msg.getMessageText();
        this.sendTime = msg.getSendTime();
        this.senderName = user.getName();
        this.senderLogin = user.getLogin();
    }
}
