package com.example.chatbot;

import retrofit2.Callback;

public interface call<T> {
    void enqueue(Callback<T> please_revert_your_question);
}
