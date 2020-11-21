package com.danvol.webchat.session;

import com.danvol.webchat.dto.ChatDto;
import com.danvol.webchat.mongo.entity.Message;
import com.danvol.webchat.mongo.entity.Notification;
import com.danvol.webchat.mongo.entity.User;

import java.util.ArrayList;
import java.util.List;

public class Session {
    static public List<UserSession> users = new ArrayList<>();

    // Пользователь зашел на сайт, создаем для него новую сессию
    static public void addNewUser(User user) {
        users.add( new UserSession(user) );
    }

    // Пользователь отправил или удалил сообщение, изменить сессию пользователя-собеседника
    static public void editUser(User user) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUuid().equals( user.getUuid() )) {
                // Пользователь найден, изменяем его сессию
                users.set(i, new UserSession(user, users.get(i).getNewChats()));
            }
        }
    }

    // Получить уведомления пользователя
    static public UserSession getUser(String uuid) throws Exception {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUuid().equals( uuid )) {
                UserSession copy = users.get(i).clone();
                users.get(i).clearNewChats();
                return copy;
            }
        }
        throw new Exception();
    }

    // Добавить в уведоления новый чат
    static  public void addNewChat(String uuid, ChatDto newChat) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUuid().equals( uuid )) {
                users.get(i).addNewChat(newChat);
            }
        }
    }

}
