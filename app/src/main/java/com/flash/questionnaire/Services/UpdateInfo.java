package com.flash.questionnaire.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.flash.questionnaire.Requests.get;
import com.flash.questionnaire.Requests.post;

/**
 * Created by fdh on 20.09.15.
 */
public class UpdateInfo extends Service {

    private get getQuests;
    private post mPost;

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if(isOnline()){
            getQuests = new get("http://quest.dev.sete.pw/api/quest.getList");
            mPost = new post("http://quest.dev.sete.pw/api/user.add", "М", "Максимов Антон", "1", "2", "3", "4", "000");
        } else{
            Log.d("my_app", "noInternet");
        }
        return START_NOT_STICKY;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
