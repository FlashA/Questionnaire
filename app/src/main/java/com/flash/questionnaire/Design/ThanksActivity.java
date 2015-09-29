package com.flash.questionnaire.Design;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;

import com.flash.questionnaire.Models.Answers;
import com.flash.questionnaire.R;
import com.flash.questionnaire.SQLite.DBDataHelper;

/**
 * Created by Anton on 20.09.2015.
 */
public class ThanksActivity extends QuestionnaireApplication {

    private Answers answers;

    private DBDataHelper DBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);

        DBHelper = new DBDataHelper(this);

        Intent intent = getIntent();
        answers = intent.getParcelableExtra("answer");

        DBHelper.addUserAnswers(answers.getSex(),
                answers.getFio(),
                answers.getPrev_quest(),
                answers.getRef(),
                answers.getRev(),
                answers.getMail(),
                answers.getTel());
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
