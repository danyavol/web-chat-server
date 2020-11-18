package com.danvol.webchat.dto;

import com.danvol.webchat.mongo.entity.Chat;
import com.danvol.webchat.mongo.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ChatDto {

    private String chatId;
    private List<MessageDto> messages;
    private MessageDto lastMessage;
    private ChatUserDto mate;

    public ChatDto(Chat chat, User userA, User userB, String type) {
        if (type.equals("getAllChats") || type.equals("getChatMessages")) {
            this.chatId = chat.getChatId();
            this.messages = createMessageDto(chat, userA, userB, false);
            if (this.messages.size() > 0)
                this.lastMessage = this.messages.get(this.messages.size()-1);
            this.mate = new ChatUserDto(userB);
        }
    }

    public ChatDto(Chat chat, User userB, String type) {
        if (type.equals("createChat")) {
            this.chatId = chat.getChatId();
            this.messages = new ArrayList<>();
            this.mate = new ChatUserDto(userB);
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

