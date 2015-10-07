package com.flash.questionnaire.Design;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.flash.questionnaire.Design.List.ListAdapter;
import com.flash.questionnaire.R;
import com.flash.questionnaire.SQLite.DBDataHelper;



public class MenuActivity extends QuestionnaireApplication {

    private DBDataHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        database = new DBDataHelper(this);
        initQuestsList();
    }

    private void initQuestsList() {
        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setDividerHeight(0);
        ListAdapter adapter = new ListAdapter(this, database.getQuests());
        listView.setAdapter(adapter);
    }

    public void exit(View v){
        ExitDialog dialog = new ExitDialog();
        dialog.setContext(this, 1);
        dialog.show(getFragmentManager(), "quest");
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideStatusBar(this);
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
