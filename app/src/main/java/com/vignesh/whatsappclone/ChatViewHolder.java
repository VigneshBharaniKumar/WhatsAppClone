package com.vignesh.whatsappclone;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ChatViewHolder extends RecyclerView.ViewHolder {

    private TextView txtUserName;
    private TextView txtMessage;

    public ChatViewHolder(@NonNull View itemView) {
        super(itemView);

        txtUserName = itemView.findViewById(R.id.txtRecyclerView_UserName_Chat);
        txtMessage = itemView.findViewById(R.id.txtRecyclerView_message_Chat);

    }

    public TextView getTxtUserName() {
        return txtUserName;
    }

    public TextView getTxtMessage() {
        return txtMessage;
    }
}
