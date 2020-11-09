package com.danvol.webchat.dto;

import com.danvol.webchat.mongo.entity.Chat;
import com.danvol.webchat.mongo.entity.Message;
import com.danvol.webchat.mongo.entity.User;
import com.danvol.webchat.mongo.repository.UsersRepository;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatDto {

    private String chatId;
    private List<MessageDto> messages;
    private ChatUserDto mate;

    public ChatDto(Chat chat, User userA, User userB, String type) {
        if (type.equals("getAllChats")) {
            this.chatId = chat.getChatId();
            this.messages = createMessageDto(chat, userA, userB, true);
            this.mate = new ChatUserDto(userB.getLogin(), userB.getName());
        } else if (type.equals("getChatMessages")) {
            this.chatId = chat.getChatId();
            this.messages = createMessageDto(chat, userA, userB, false);
        }
    }

    public ChatDto(Chat chat, User userB, String type) {
        if (type.equals("createChat")) {
            this.chatId = chat.getChatId();
            this.messages = new ArrayList<>();
            this.mate = new ChatUserDto(userB.getLogin(), userB.getName());
        }
    }


    private List<MessageDto> createMessageDto(Chat chat, User userA, User userB, boolean onlyLast) {
        // Массив пользователей чата
        List<User> users = new ArrayList<>();
        users.add(userA);
        users.add(userB);


        List<MessageDto> messages = new ArrayList<>();
        if (onlyLast) {
            // Только последнее сообщение
            int size = chat.getMessages().size();
            if (size != 0) {
                messages.add( new MessageDto( chat.getMessages().get(size-1), users ) );
            }
        } else {
            // Все сообщения
            for (int i = 0; i < chat.getMessages().size(); i++) {
                messages.add( new MessageDto( chat.getMessages().get(i), users ) );
            }
        }


        return messages;
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

