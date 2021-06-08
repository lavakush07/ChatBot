package com.example.chatbot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView chatsRV;
    private EditText userMsgEdt;
    private FloatingActionButton sendMsgFAB;
private final String BOT_KEY="bot";
private final String USER_KEY="user";
private ArrayList<chat_modal>chatModalArrayList;
private ChatRvAdapter chatRvAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chatsRV=findViewById(R.id.idRVChats);
        userMsgEdt=findViewById(R.id.idEdtMSg);
        sendMsgFAB=findViewById(R.id.idFABSend);
       chatModalArrayList=new ArrayList<>();
       chatRvAdapter=new ChatRvAdapter(chatModalArrayList,this);
        LinearLayoutManager manager=new LinearLayoutManager(this);
chatsRV.setAdapter(chatRvAdapter);
chatsRV.setLayoutManager(manager);
sendMsgFAB.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
if(userMsgEdt.getText().toString().isEmpty()){
    Toast.makeText(MainActivity.this,"please enter your message",Toast.LENGTH_SHORT).show();
    return;
    }
getResponse(userMsgEdt.getText().toString());
userMsgEdt.setText("");
}
});

    }
    private void getResponse(String message) {
        chatModalArrayList.add(new chat_modal(message, USER_KEY));
        chatRvAdapter.notifyDataSetChanged();
        String url =
                "http://api.brainshop.ai/get?bid=156814&key=WpRWOvUTy7yaryOb&uid=[uid]&msg=" + message;

        String BASE_URL = "http://api.brainshop.ai/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        call<msg_model> call = retrofitAPI.getMessage(url);
        call.enqueue(new Callback<msg_model>() {

            @Override
            public void onResponse(Call<msg_model> call, Response<msg_model> response) {
                if (response.isSuccessful()) {
                    msg_model modal = response.body();
                    chatModalArrayList.add(new chat_modal(modal.getCnt(), BOT_KEY));
                }
            }

            @Override
            public void onFailure(Call<msg_model> call, Throwable t) {


                chatModalArrayList.add(new chat_modal("please revert your question", BOT_KEY));
                chatRvAdapter.notifyDataSetChanged();
            }


        });




    }


}