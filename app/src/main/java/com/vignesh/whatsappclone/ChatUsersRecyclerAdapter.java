package com.vignesh.whatsappclone;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ChatUsersRecyclerAdapter extends RecyclerView.Adapter<ChatUsersViewHolder> {

    private ArrayList<String> users;
    private onClickUserInterface onClickUserInterface;

    public interface onClickUserInterface {

        void onClickUser(String selectedUser);

    }

    public ChatUsersRecyclerAdapter(ArrayList<String> users, ChatUsersRecyclerAdapter.onClickUserInterface onClickUserInterface) {
        this.users = users;
        this.onClickUserInterface = onClickUserInterface;
    }

    @NonNull
    @Override
    public ChatUsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_holder_chat_users, parent, false);
        ChatUsersViewHolder chatUsersViewHolder = new ChatUsersViewHolder(view);

        return chatUsersViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChatUsersViewHolder holder, final int position) {
        holder.getTxtUserName().setText(users.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickUserInterface.onClickUser(users.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
}
