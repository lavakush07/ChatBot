package com.example.chatbot;

public class chat_modal {
    private String message;
    private String sender;

    public chat_modal(String sender,String message) {
        this.sender = sender;
        this.message=message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
