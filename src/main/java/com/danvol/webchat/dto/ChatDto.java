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

    public ChatDto(Chat obj, User userB, String type, UsersRepository usersRepository) {
        if (type.equals("createChat")) {
            this.chatId = obj.getChatId();
            this.messages = new ArrayList<>();
            this.mate = new ChatUserDto(userB.getLogin(), userB.getName());
        } else if (type.equals("getAllChats")) {
            this.chatId = obj.getChatId();
            this.messages = createMessageDto(obj, true, usersRepository);
            this.mate = new ChatUserDto(userB.getLogin(), userB.getName());
        }
    }

    public ChatDto(Chat chat, String type, UsersRepository usersRepository) {
        if (type.equals("getChatMessages")) {
            this.chatId = chat.getChatId();
            this.messages = createMessageDto(chat, usersRepository);
        }
    }


    private List<MessageDto> createMessageDto(Chat chat, boolean onlyLast, UsersRepository usersRepository) {
        // Поиск информации по каждому пользователю
        List<User> users = new ArrayList<>();
        for (int i = 0; i < chat.getUsers().size(); i++) {
            users.add( usersRepository.findByUserId( chat.getUsers().get(i).getUserId() ) );
        }


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

    private List<MessageDto> createMessageDto(Chat chat, UsersRepository usersRepository) {
        return createMessageDto(chat, false, usersRepository);
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

@Data
class MessageDto {
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
}
