package com.flash.questionnaire.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.flash.questionnaire.Models.Quest;
import com.flash.questionnaire.Models.UsersData;
import com.flash.questionnaire.Requests.get;
import com.flash.questionnaire.Requests.post;
import com.flash.questionnaire.SQLite.DBDataHelper;
import com.flash.questionnaire.Utils.Constants;

/**
 * Created by fdh on 20.09.15.
 */
public class UpdateInfo extends Service {

    private get getQuests;
    private post mPost;

    private DBDataHelper DBHelper;
    private Quest mQuest;
    private UsersData usersData;

    public void onCreate() {
        super.onCreate();
        DBHelper = new DBDataHelper(getApplicationContext());
        mQuest = new Quest();
        usersData = new UsersData();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if(isOnline()){
            addQuests();
            //addUsers();
        }
        return START_NOT_STICKY;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void addUsers(){
        if(DBHelper.getSizeUsers()){
            mPost = new post(getApplicationContext());
            Log.d("my_app", "JSON Data: " + DBHelper.composeJson());
        }
    }

    public void addQuests(){
        getQuests = new get(Constants.API_URL_GET, getApplicationContext());
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
