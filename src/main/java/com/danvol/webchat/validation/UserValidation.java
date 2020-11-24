package com.danvol.webchat.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidation {

    public static String name(String name) {
        if (name == null) return "";
        name = name.trim();

        Pattern pattern = Pattern.compile("(^[А-Я][а-я]{2,14}$|^[A-Z][a-z]{2,14}$)");
        Matcher matcher = pattern.matcher(name);

        if (matcher.matches()) return name;
        else return "";
    }

    public static String login(String login) {
        if (login == null) return "";
        login = login.trim();

        Pattern pattern = Pattern.compile("^[a-z][0-9a-z]{3,9}$");
        Matcher matcher = pattern.matcher(login);

        if (matcher.matches()) return login;
        else return "";
    }

    public static String password(String password) {
        if (password == null) return "";
        password = password.trim();

        Pattern pattern = Pattern.compile("^[0-9a-zA-Z]{4,20}$");
        Matcher matcher = pattern.matcher(password);

        if (matcher.matches()) return password;
        else return "";
    }

}
