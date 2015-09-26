package com.flash.questionnaire.Design;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.flash.questionnaire.Design.List.ListAdapter;
import com.flash.questionnaire.Kiosk.StatusBarManager;
import com.flash.questionnaire.R;
import com.flash.questionnaire.SQLite.DBDataHelper;

/**
 * Created by Anton on 20.09.2015.
 */
public class MenuActivity extends QuestionnaireApplication {

    private ListView listView;
    private ListAdapter adapter;
    private DBDataHelper database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        database = new DBDataHelper(this);
        initQuestsList();

        if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {
            Toast.makeText(this, "Large screen",Toast.LENGTH_LONG).show();

        }
        else if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            Toast.makeText(this, "Normal sized screen" , Toast.LENGTH_LONG).show();

        }
        else if ((getResources().getConfiguration().screenLayout &      Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_SMALL) {
            Toast.makeText(this, "Small sized screen" , Toast.LENGTH_LONG).show();
        }
        else {
            Toast.makeText(this, "Screen size is neither large, normal or small" , Toast.LENGTH_LONG).show();
        }

    }

    private void initQuestsList() {
        listView = (ListView) findViewById(R.id.listView);
        listView.setDividerHeight(0);
        adapter = new ListAdapter(this, database.getQuests());

  //      Log.d("my_app", database.getQuests().get(0));
        listView.setAdapter(adapter);
    }

    public void start(View v){
        startActivity(new Intent(this, QuestionnaireActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideStatusBar(this);
    }

}
