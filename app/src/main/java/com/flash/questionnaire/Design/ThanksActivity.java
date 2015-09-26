package com.flash.questionnaire.Design;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.flash.questionnaire.R;

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
        ExitDialog dialog = new ExitDialog();
        dialog.setContext(this);
        dialog.show(getFragmentManager(), "quest");

    }

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
