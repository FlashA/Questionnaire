package com.flash.questionnaire.Design;

import android.app.ActionBar;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import com.flash.questionnaire.Kiosk.CustomViewGroup;
import com.flash.questionnaire.Kiosk.StatusBarManager;
import com.flash.questionnaire.MainActivity;
import com.flash.questionnaire.QuestionnaireApplication;
import com.flash.questionnaire.R;
import com.flash.questionnaire.Utils.Constants;
import com.flash.questionnaire.Utils.ContextHelper;

/**
 * Created by Anton on 20.09.2015.
 */
public class ThanksActivity extends QuestionnaireApplication {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);


    }

    public void restart(View v){
        startActivity(new Intent(this, MenuActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideStatusBar(this);
    }

    public void exit(View v) {
        showChooser();

    }
/*    protected void hideStatusBar(Context context) {
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
*/

    void showChooser() {

        PackageManager pm = getPackageManager();
        ComponentName cm = new ComponentName(this, FakeActivity.class);
        pm.setComponentEnabledSetting(cm, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);

        runDefaultApp();

        pm.setComponentEnabledSetting(cm, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }
    void runDefaultApp(){
        Intent selector = new Intent(Intent.ACTION_MAIN);
        selector.addCategory(Intent.CATEGORY_HOME);
        selector.addCategory(Intent.CATEGORY_DEFAULT);
        startActivity(selector);
    }
}
