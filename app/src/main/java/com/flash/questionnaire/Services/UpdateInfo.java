package com.flash.questionnaire.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.flash.questionnaire.Models.Quests;
import com.flash.questionnaire.Utils.Constants;

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
        return super.onStartCommand(intent, flags, startId);
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
}
