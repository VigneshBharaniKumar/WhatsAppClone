package com.vignesh.whatsappclone;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseInstallation;

import java.util.ArrayList;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build()
        );

        ArrayList<String> channels = new ArrayList<>();
        channels.add("News");

        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
        installation.put("GCMSenderId", "845530556047");
        installation.put("channels", channels);
        installation.saveInBackground();

    }

}
