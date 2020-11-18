package com.danvol.webchat.mongo.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@Document(collection = "chats")
@NoArgsConstructor
@Data
public class Chat {
    @Id private String chatId;
    private int messagesCount;
    private List<Message> messages;
    private List<ChatUser> users;

    public Chat(List<ChatUser> users) {
        this.users = users;
        this.messagesCount = 0;
        this.messages = new ArrayList<>();
    }
}
