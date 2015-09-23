package com.flash.questionnaire;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Switch;

import com.flash.questionnaire.Kiosk.StatusBarManager;
import com.flash.questionnaire.Utils.Constants;
import com.flash.questionnaire.Utils.ContextHelper;

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

}
