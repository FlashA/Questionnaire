package com.flash.questionnaire.Design;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.flash.questionnaire.R;
import com.flash.questionnaire.SQLite.DBDataHelper;
import com.flash.questionnaire.Services.UpdateInfo;

import java.util.Calendar;

public class DesignActivity extends QuestionnaireApplication {

    private DBDataHelper DBHelper;

    private Handler mHandler = new Handler();
    private boolean isRunning = true;

    private ProgressBar progressBar;
    private TextView textView_status;

    private Thread thread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);

        textView_status = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        textView_status.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        DBHelper = new DBDataHelper(getApplicationContext());

        startService();
        await();
    }

    public void await(){
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRunning) {
                    try {
                        Thread.sleep(1000);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                if(!DBHelper.getSizes()) startService();
                                if(DBHelper.getSizes()) {
                                    isRunning = false;
                                    thread.interrupt();
                                    startActivity(new Intent(DesignActivity.this, MenuActivity.class));
                                    finish();
                                }
                                if(!isOnline()){
                                    textView_status.setVisibility(View.VISIBLE);
                                    textView_status.setText("Отсутствует доступ к интернету...");
                                }
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
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

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideStatusBar(this);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
