package com.flash.questionnaire.Design;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.flash.questionnaire.Design.List.ListAdapter;
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
    }

    private void initQuestsList() {
        listView = (ListView) findViewById(R.id.listView);
        listView.setDividerHeight(0);
        adapter = new ListAdapter(this, database.getQuests());
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
