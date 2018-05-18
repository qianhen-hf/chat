package com.fan.common;


import com.fan.po.User;

public class UserHolder {

    private static final ThreadLocal<User> userHolder = new ThreadLocal<User>();


    public static void add(User user) {
        userHolder.set(user);
    }


    public static User getCurrentUser() {
        return userHolder.get();
    }


    public static void remove() {
        userHolder.remove();
    }
}
