package com.vignesh.whatsappclone;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatUsersViewHolder extends RecyclerView.ViewHolder {

    private ImageView userImage;
    private TextView txtUserName;

    public ChatUsersViewHolder(@NonNull View itemView) {
        super(itemView);

        userImage = itemView.findViewById(R.id.imgRecyclerView_ChatsImage);
        txtUserName = itemView.findViewById(R.id.txtRecyclerView_UserName_Chats);

    }

    public ImageView getUserImage() {
        return userImage;
    }

    public TextView getTxtUserName() {
        return txtUserName;
    }
}
