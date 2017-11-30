package com.example.aldi.androidkopiku.model;

/**
 * Created by aldi on 11/02/17.
 */

public class User {
    private String Email;
    private String Password;
    private String Name;

    public User() {
    }

    public User(String email, String password, String name) {
        Email = email;
        Password = password;
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}
