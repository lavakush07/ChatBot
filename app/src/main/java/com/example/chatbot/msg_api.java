package com.example.chatbot;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface msg_api {
    @GET
    Call<msg_model>getMessage(@Url String url);
}
