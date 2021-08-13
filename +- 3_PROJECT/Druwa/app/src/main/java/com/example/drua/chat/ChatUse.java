package com.example.drua.chat;

public class ChatUse {

    private String userName;
    private String message;

    public ChatUse() {}
    public ChatUse(String userName, String message) {
        this.userName = userName;
        this.message = message;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUserName() {
        return userName;
    }

    public String getMessage() {
        return message;
    }
}