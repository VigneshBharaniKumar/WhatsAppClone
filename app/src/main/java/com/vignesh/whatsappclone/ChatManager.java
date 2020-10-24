package com.vignesh.whatsappclone;

import android.content.Context;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ChatManager {

    private Context mContext;

    private SweetAlertDialog alertDialog;

    private ParseObject object = new ParseObject("Chat");

    private static final String SENDER_KEY = "sender";
    private static final String RECEIVER_KEY = "receiver";
    private static final String MESSAGE_KEY = "message";

    public ChatManager(Context mContext) {
        this.mContext = mContext;
    }

    public void sendMessage(final Message message) {

        object.put(SENDER_KEY, message.getSender());
        object.put(RECEIVER_KEY, message.getReceiver());
        object.put(MESSAGE_KEY, message.getMessage());

        object.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {

                if (e == null) {
                    message.setObjectId(object.getObjectId());
                } else {
                    alertDialog = new SweetAlertDialog(mContext, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Failed")
                            .setContentText("Error: " + e);
                    alertDialog.show();
                }

            }
        });

    }

}
