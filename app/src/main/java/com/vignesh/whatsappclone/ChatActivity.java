package com.vignesh.whatsappclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputLayout;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class ChatActivity extends AppCompatActivity {

    private MaterialToolbar toolbar;

    private TextInputLayout edtMessage;
    private Button btnSend;

    private SwipyRefreshLayout pullToRefreshLayout;

    private String selectedUser;

    private RecyclerView recyclerView;

    private SweetAlertDialog alertDialog;

    private ChatManager mChatManager;

    private Message message;

    private static final String SENDER_KEY = "sender";
    private static final String RECEIVER_KEY = "receiver";
    private static final String MESSAGE_KEY = "message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        toolbar = findViewById(R.id.toolbar);
        edtMessage = findViewById(R.id.edtMessage_chat);
        btnSend = findViewById(R.id.btnSend_chat);
        pullToRefreshLayout = findViewById(R.id.pullToRefreshLayout);
        recyclerView = findViewById(R.id.recyclerView_chat);

        selectedUser = getIntent().getStringExtra("selectedUser");

        setSupportActionBar(toolbar);
        setTitle(selectedUser);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!edtMessage.getEditText().getText().toString().equals("")) {

                    mChatManager = new ChatManager(ChatActivity.this);
                    message = new Message(ParseUser.getCurrentUser().getUsername(), selectedUser, edtMessage.getEditText().getText().toString());
                    mChatManager.sendMessage(message);
                    edtMessage.getEditText().setText("");
                    refreshChat();

                }
            }
        });

        pullToRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                refreshChat();
            }
        });

    }

    public void refreshChat() {

        pullToRefreshLayout.setRefreshing(true);

        final ArrayList<Message> messages = new ArrayList<>();

        ParseQuery<ParseObject> query1 = new ParseQuery<>("Chat");
        query1.whereEqualTo(SENDER_KEY, ParseUser.getCurrentUser().getUsername());
        query1.whereEqualTo(RECEIVER_KEY, selectedUser);

        ParseQuery<ParseObject> query2 = new ParseQuery<>("Chat");
        query2.whereEqualTo(SENDER_KEY, selectedUser);
        query2.whereEqualTo(RECEIVER_KEY, ParseUser.getCurrentUser().getUsername());

        List<ParseQuery<ParseObject>> list = new ArrayList<>();
        list.add(query1);
        list.add(query2);

        ParseQuery<ParseObject> query = ParseQuery.or(list);
        query.orderByAscending("createdAt");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if (e == null) {
                    pullToRefreshLayout.setRefreshing(false);

                    for (ParseObject obj : objects) {

                        Message message = new Message(
                                obj.get(SENDER_KEY).toString(),
                                obj.get(RECEIVER_KEY).toString(),
                                obj.get(MESSAGE_KEY).toString()
                        );

                        messages.add(message);

                    }

                    recyclerView.setAdapter(new ChatRecyclerAdapter(messages));
                    recyclerView.setLayoutManager(new LinearLayoutManager(ChatActivity.this));
                    recyclerView.post(new Runnable() {
                        @Override
                        public void run() {
                            if (messages.size() > 1)
                            recyclerView.scrollToPosition(messages.size()- 1);
                        }
                    });
                }

                else {
                    pullToRefreshLayout.setRefreshing(false);

                    alertDialog = new SweetAlertDialog(ChatActivity.this, SweetAlertDialog.ERROR_TYPE)
                            .setContentText("Error : " + e);
                    alertDialog.show();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshChat();
    }
}
