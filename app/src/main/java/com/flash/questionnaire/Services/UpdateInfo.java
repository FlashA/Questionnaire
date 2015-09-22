package com.flash.questionnaire.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.flash.questionnaire.API.API;
import com.flash.questionnaire.Models.JSON;
import com.flash.questionnaire.Models.PostRequest;
import com.flash.questionnaire.Models.QuestsInfo;
import com.flash.questionnaire.Models.Task;
import com.flash.questionnaire.Utils.Constants;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by fdh on 20.09.15.
 */
public class UpdateInfo extends Service {

    private QuestsInfo questsInfo;

    public void onCreate() {
        super.onCreate();
        Log.d("my_app", "onCreate Service");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("my_app", "onStartCommand");
        if(isOnline()){
            Log.d("my_app", "isInternet");
            getQuests();
            //sendUser("М", "Иванов Петр Иванович", "1", "2", "3", "4", "5");
            //addUser();
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

    public void getQuests(){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(Constants.API_URL)
                .build();

        API api = restAdapter.create(API.class);
        api.getQuests(new Callback<JSON>() {
            @Override
            public void success(JSON json, Response response) {
                Log.d("my_app", "Success");
                Log.d("my_app", "Info: " + json.getStatus());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("my_app", "Failure");
            }
        });
    }

    public void sendUser(String sex, String fio, String prev_quest, String ref
    , String rev, String mail, String tel){
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.API_URL)
                .build();
        API api = restAdapter.create(API.class);
        api.addUser(sex, fio, prev_quest, ref, rev, mail, tel, new Callback<PostRequest>() {
            @Override
            public void success(PostRequest postRequest, Response response) {
                Log.d("my_app", "Success POST Request");
                if (postRequest.getStatus().equals("success")) {
                    Log.d("my_app", "Success");
                } else {
                    Log.d("my_app", "Error");
                }
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("my_app", "Fail: " + error);
                Log.d("my_app", "Failure POST Request");
            }
        });
    }

    public void addUser() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Constants.API_URL)
                .build();
        API api = restAdapter.create(API.class);
        for (int i = 0; i < 2; i++) {
            api.users((new Task("Titel_Steffen", "Titel_Steffen", "Titel_Steffen", "Titel_Steffen", "Titel_Steffen",
                    "Titel_Steffen", "Titel_Steffen")), (new Callback<Task>() {
                @Override
                public void success(Task task, Response response) {
                    Log.i("my_app", "True");
                }

                @Override
                public void failure(RetrofitError error) {
                    Log.e("my_app", "False");
                }
            }));
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
