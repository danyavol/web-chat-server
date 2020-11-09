package com.danvol.webchat.service;


import com.danvol.webchat.dto.ChatDto;
import com.danvol.webchat.dto.MessageDto;
import com.danvol.webchat.exception.RequestException;
import com.danvol.webchat.mongo.entity.Chat;
import com.danvol.webchat.mongo.entity.ChatUser;
import com.danvol.webchat.mongo.entity.Message;
import com.danvol.webchat.mongo.entity.User;
import com.danvol.webchat.mongo.repository.ChatsRepository;
import com.danvol.webchat.mongo.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatsServiceImpl implements ChatsService {
    @Autowired
    private ChatsRepository chatsRepository;
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public ResponseEntity createChat(String userId, String login) {
        User userA = usersRepository.findByUserId(userId);
        User userB = usersRepository.findByLogin(login);
        if (userA == null)
            return new ResponseEntity<>(new RequestException("Неверный id пользователя"), HttpStatus.OK);
        if (userB == null)
            return new ResponseEntity<>(new RequestException("Пользователь с таким логином не найден"), HttpStatus.OK);

        // Создание списка пользователей для класса Chat
        List<ChatUser> chatUsers = new ArrayList<>();
        chatUsers.add( new ChatUser(userId) );
        chatUsers.add( new ChatUser(userB.getUserId()) );

        // Сортировка списка пользователей
        chatUsers.sort(ChatUser::compareTo);

        Chat chat;

        // Поиск такого чата в БД
        chat = chatsRepository.findByUsers(chatUsers);
        if (chat == null) {
            // Если чат не найден, создаем новый
            chat = new Chat(chatUsers);
            chatsRepository.save(chat);
        }

        return new ResponseEntity<>(new ChatDto(chat, userB, "createChat"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity getAllChats(String userId) {
        User userA = usersRepository.findByUserId(userId);
        if (userA == null)
            return new ResponseEntity<>(new RequestException("Пользователь с таким id не найден"), HttpStatus.OK);

        List<Chat> allChats = chatsRepository.findAll();
        List<Chat> userChats = new ArrayList<>();
        // Поиск нужных чатов среди всего списка
        for (int i = 0; i < allChats.size(); i++) {
            for (int j = 0; j < allChats.get(i).getUsers().size(); j++) {
                if (allChats.get(i).getUsers().get(j).getUserId().equals(userId)) {
                    userChats.add( allChats.get(i) );
                }
            }
        }

        List<ChatDto> userChatsDto = new ArrayList<>();
        for (int i = 0; i < userChats.size(); i++) {
            // Выборка айди собеседеника
            String userBId = userChats.get(i).getUsers().get(0).getUserId();
            if (userBId.equals(userId)) userBId = userChats.get(i).getUsers().get(1).getUserId();

            // Поиск собеседника в БД
            User userB = usersRepository.findByUserId(userBId);
            userChatsDto.add( new ChatDto(userChats.get(i), userA, userB, "getAllChats") );
        }

        return new ResponseEntity<>(userChatsDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getChatMessages(String userId, String chatId) {
        User userA = usersRepository.findByUserId(userId);
        if (userA == null)
            return new ResponseEntity<>(new RequestException("Пользователь с таким id не найден"), HttpStatus.OK);

        Chat chat = chatsRepository.findByChatId(chatId);
        if (chat == null)
            return new ResponseEntity<>(new RequestException("Чат с таким id не найден"), HttpStatus.OK);

        // Айди второго собеседника в чате
        String userBId = chat.getUsers().get(0).getUserId();
        if (userBId.equals(userA.getUserId())) userBId = chat.getUsers().get(1).getUserId();

        User userB = usersRepository.findByUserId(userBId);

        return new ResponseEntity<>(new ChatDto(chat, userA, userB, "getChatMessages"), HttpStatus.OK);
    }

    @Override
    public ResponseEntity newMessage(String userId, String chatId, String message) {
        User user = usersRepository.findByUserId(userId);
        if (user == null) return new ResponseEntity<>(new RequestException("Неверный id пользователя"), HttpStatus.OK);
        Chat chat = chatsRepository.findByChatId(chatId);
        if (chat == null) return new ResponseEntity<>(new RequestException("Неверный id чата"), HttpStatus.OK);

        if(message.length() == 0) return new ResponseEntity<>(new RequestException("Сообщение не может быть пустым"), HttpStatus.OK);
        if(message.length() > 1000) return new ResponseEntity<>(new RequestException("Сообщение не может быть длиннее 1000 символов"), HttpStatus.OK);

        // Проверка есть ли user в чате chat
        boolean flag = true;
        for (int i = 0; i < chat.getUsers().size(); i++) {
            if (userId.equals(chat.getUsers().get(i).getUserId())) {
                flag = false;
                break;
            }
        }
        if (flag)
            return new ResponseEntity<>(new RequestException("Нет доступа к данному чату"), HttpStatus.OK);

        // Создание нового сообщения
        Message msg = new Message(userId, message);

        List<Message> messages = chat.getMessages();
        // Создание messageId
        int lastId = 0;
        if (messages.size() != 0)
            lastId = messages.get(messages.size()-1).getMessageId();
        msg.setMessageId(++lastId);
        messages.add( msg );

        // Сохранение чата в БД
        chatsRepository.save(chat);

        return new ResponseEntity<>(new MessageDto(msg, user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity deleteMessage(String userId, String chatId, int messageId) {
        User user = usersRepository.findByUserId(userId);
        if (user == null) return new ResponseEntity<>(new RequestException("Неверный id пользователя"), HttpStatus.OK);
        Chat chat = chatsRepository.findByChatId(chatId);
        if (chat == null) return new ResponseEntity<>(new RequestException("Неверный id чата"), HttpStatus.OK);

        // Проверка есть ли user в чате chat
        boolean flag = true;
        for (int i = 0; i < chat.getUsers().size(); i++) {
            if (userId.equals(chat.getUsers().get(i).getUserId())) {
                flag = false;
                break;
            }
        }
        if (flag)
            return new ResponseEntity<>(new RequestException("Нет доступа к данному чату"), HttpStatus.OK);

        // Удаление сообщения
        flag = true;
        for (int i = 0; i < chat.getMessages().size(); i++) {
            if (messageId == chat.getMessages().get(i).getMessageId()) {
                chat.getMessages().remove(i);
                flag = false;
                break;
            }
        }
        if (flag)
            return new ResponseEntity<>(new RequestException("Сообщение с таким id не найдено"), HttpStatus.OK);

        chatsRepository.save(chat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
