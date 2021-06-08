package com.example.chatbot;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatRvAdapter extends RecyclerView.Adapter {
private ArrayList<chat_modal> chat_modalArrayList;
private Context context;

    public ChatRvAdapter(ArrayList<chat_modal> chat_modalArrayList,Context context) {
        this.chat_modalArrayList = chat_modalArrayList;
        this.context=context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch(viewType )
        {
            case 0:
                view= LayoutInflater.from(parent.getContext()).inflate(R.layout.user_layout,parent,false);
                        return new UserViewHolder(view);
            case 1:
                view=LayoutInflater.from(parent.getContext()).inflate(R.layout.chatbot_layout,parent,false);
                        return new BotViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
  chat_modal chatModal=chat_modalArrayList.get(position);
  switch (chatModal.getSender())
  {
      case "user":
          ((UserViewHolder)holder).userTV.setText(chatModal.getMessage());
          break;
      case "Bot":
          ((BotViewHolder)holder).botMsgTv.setText(chatModal.getMessage());

  }
    }
    @Override
    public int getItemViewType(int position)
    {
       switch(chat_modalArrayList.get(position).getSender())
       {
           case "user":
               return 0;
           case "bot":
               return 1;
           default:
               return -1;

       }
    }

    @Override
    public int getItemCount() {
        return chat_modalArrayList.size();
    }
    public static class UserViewHolder extends RecyclerView. ViewHolder{

        TextView userTV;

        public UserViewHolder(@NonNull View itemView) {

            super(itemView);
            userTV = itemView.findViewById(R.id.idTVUser);
        }
    }
    public static class BotViewHolder extends RecyclerView.ViewHolder{
TextView botMsgTv;
        public BotViewHolder(@NonNull View itemView) {
            super(itemView);
            botMsgTv=itemView.findViewById(R.id.idBotUser);
        }
    }
}
