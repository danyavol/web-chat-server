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
    private ChatUserDto sender;
    private Date sendTime;

    public MessageDto(Message msg, List<User> users) {
        this.messageId = msg.getMessageId();
        this.messageText = msg.getMessageText();
        this.sendTime = msg.getSendTime();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUserId().equals(msg.getSenderId())) {
                this.sender = new ChatUserDto( users.get(i) );
            }
        }

    }

    public MessageDto(Message msg, User user) {
        this.messageId = msg.getMessageId();
        this.messageText = msg.getMessageText();
        this.sendTime = msg.getSendTime();
        this.sender = new ChatUserDto( user );
    }
}
