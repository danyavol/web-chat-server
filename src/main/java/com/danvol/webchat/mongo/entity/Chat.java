package com.danvol.webchat.mongo.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Document(collection = "chats")
@Data
public class Chat {
    @Id private String chatId;
    private List<Message> messages;
    private List<User> users;
}
