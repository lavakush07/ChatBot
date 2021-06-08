package com.example.chatbot;

import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitAPI {

@GET
    call<msg_model>getMessage(@Url  String url);
}
