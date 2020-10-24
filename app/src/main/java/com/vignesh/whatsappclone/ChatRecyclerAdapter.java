package com.vignesh.whatsappclone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.parse.ParseUser;

import java.util.ArrayList;

public class ChatRecyclerAdapter extends RecyclerView.Adapter<ChatViewHolder> {

    private ArrayList<Message> messages;

    public ChatRecyclerAdapter(ArrayList<Message> messages) {
        this.messages = messages;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_holder_chat, parent, false);
        ChatViewHolder chatViewHolder = new ChatViewHolder(view);

        return chatViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {

        if (messages.get(position).getSender().equals(ParseUser.getCurrentUser().getUsername())) {
            holder.getTxtUserName().setText(messages.get(position).getSender() + " : ");
            //holder.getTxtMessage().setText(messages.get(position).getMessage());
        } else if (messages.get(position).getReceiver().equals(ParseUser.getCurrentUser().getUsername())) {
            holder.getTxtUserName().setText(messages.get(position).getSender()+ " : ");
            //holder.getTxtMessage().setText(messages.get(position).getMessage());
        }
        holder.getTxtMessage().setText(messages.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }
}
