package com.flash.questionnaire;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.flash.questionnaire.SQLite.DBDataHelper;
import com.flash.questionnaire.Services.UpdateInfo;

import java.util.Calendar;

/**
 * Created by Anton on 20.09.2015.
 */
public class FuncActivity extends AppCompatActivity {

    private DBDataHelper DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_func);
        startService();
        DBHelper = new DBDataHelper(getApplicationContext());
    }

    public void onClick(View v){
        //Toast.makeText(getApplicationContext(), DBHelper.getQuestions(4, 1), Toast.LENGTH_SHORT).show();
        String listQuest = "";
        for(String s: DBHelper.getQuests()) listQuest += s + "\t";
        Log.d("my_app", "Название квеста: " + listQuest);
    }

    public void checkData(){
        if(!DBHelper.getSizes()){
            // зпаустить диалог
        }
    }

    public void startService(){
        startService(new Intent(this, UpdateInfo.class));
        Calendar cal = Calendar.getInstance();
        Intent intent = new Intent(this, UpdateInfo.class);
        PendingIntent pintent = PendingIntent
                .getService(this, 0, intent, 0);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                3600 * 1000, pintent);
    }
}
