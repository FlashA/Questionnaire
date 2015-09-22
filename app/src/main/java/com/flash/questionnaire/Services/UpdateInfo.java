package com.flash.questionnaire.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.flash.questionnaire.API.API;
import com.flash.questionnaire.Models.Quests;
import com.flash.questionnaire.Utils.Constants;

import java.net.InetAddress;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by fdh on 20.09.15.
 */
public class UpdateInfo extends Service {

    private String TAG = getPackageName().toString();

    public void onCreate() {
        super.onCreate();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        if(isInternetAvailable()){
            getQuests();
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

    public void getQuests(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Constants.API_URL)
                .build();

        API api = restAdapter.create(API.class);
        api.getQuests(new Callback<Quests>() {
            @Override
            public void success(Quests quests, Response response) {
                Log.d(TAG, "Success");
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, "Failure");
            }
        });
    }

    public void sendUser(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.API_URL)
                .build();
        API api = restAdapter.create(API.class);
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("http://www.yandex.ru");
            if (ipAddr.equals("")) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            return false;
        }
    }
}
