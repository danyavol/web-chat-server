package com.danvol.webchat.dto;

import com.danvol.webchat.mongo.entity.Chat;
import com.danvol.webchat.mongo.entity.Message;
import com.danvol.webchat.mongo.entity.User;
import com.danvol.webchat.mongo.repository.UsersRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Entity;
import java.util.List;

@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatDto {
    @Autowired
    private UsersRepository usersRepository;

    private String chatId;
    private List<Message> messages;
    private ChatUserDto mate;

    public ChatDto(Chat obj, User userB, String type) {
        if (type.equals("createChat")) {
            this.chatId = obj.getChatId();
            this.messages = obj.getMessages();
            this.mate = new ChatUserDto(userB.getLogin(), userB.getName());
        }
    }
}

@Data
class ChatUserDto {
    private String login;
    private String name;

    public ChatUserDto(String login, String name) {
        this.login = login;
        this.name = name;
    }
}
