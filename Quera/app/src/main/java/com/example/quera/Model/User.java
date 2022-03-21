package com.example.quera.Model;

public class User {
    private String username;
    private String password;
    private String name;

    User(String name,String username , String password) {
        this.name= name;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }
}
