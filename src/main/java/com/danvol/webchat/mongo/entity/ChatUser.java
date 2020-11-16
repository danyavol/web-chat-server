package com.danvol.webchat.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import javax.persistence.Entity;


@Entity
@Data
public class ChatUser {
    @Id private String userId;

    public ChatUser(String userId) {
        this.userId = userId;
    }

    public static int compareTo(ChatUser chatUser, ChatUser chatUser1) {
        return chatUser.getUserId().compareTo(chatUser1.getUserId());
    }
}
