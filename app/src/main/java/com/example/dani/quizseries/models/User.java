package com.example.dani.quizseries.models;

/**
 * Created by dani on 10/09/17.
 */

public class User {
    private String user_id;
    private String name;

    public User(String user_id, String name) {
        this.user_id = user_id;
        this.name = name;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getName() {
        return name;
    }
}
