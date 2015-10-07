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

import java.util.ArrayList;

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
            addUsers();
        } else{
            Log.d("my_app", "noInternet");
        }
        return START_NOT_STICKY;
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public void addUsers(){
        ArrayList<UsersData> users = DBHelper.getUsers();
        for (int i = 0; i < users.size(); i++) {
            mPost = new post(Constants.API_URL_POST,
                    users.get(i).getSex(),
                    users.get(i).getFio(),
                    users.get(i).getRef(),
                    users.get(i).getRev(),
                    users.get(i).getMail(),
                    users.get(i).getTel(),
                    getApplicationContext());
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
