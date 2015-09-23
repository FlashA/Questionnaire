package com.flash.questionnaire.Design;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.flash.questionnaire.Kiosk.StatusBarManager;

/**
 * Created by Anton on 22.09.2015.
 */
public class QuestionnaireApplication extends AppCompatActivity {

    StatusBarManager statusBarManager;

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (!hasFocus) {
            Intent closeDialog =
                    new Intent(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
            sendBroadcast(closeDialog);
        }
    }

    protected void hideStatusBar(Context context) {
        statusBarManager = new StatusBarManager(context);
        statusBarManager.hide();
    }

    protected void showStatusBar() {
        statusBarManager.show();
    }


    @Override
    protected void onPause() {
        super.onPause();
        showStatusBar();
    }




    @Override
    public void onBackPressed() {
    }

    @Override
    public void onStart() {
        super.onStart();
        appToFront();
    }
    protected void appToFront() {
        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);

        activityManager.moveTaskToFront(getTaskId(), 0);
    }

}
