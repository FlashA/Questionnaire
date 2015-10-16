package com.flash.questionnaire.Design;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.flash.questionnaire.Models.Answers;
import com.flash.questionnaire.R;
import com.flash.questionnaire.SQLite.DBDataHelper;

/**
 * Created by Anton on 20.09.2015.
 */
public class AboutActivity extends QuestionnaireApplication {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText("    Developers:\n" +
                "\n" +
                "        Maksimov Anton\n" +
                "            Contacts:\n" +
                "                E-Mail: flashteso@gmail.com\n" +
                "                Skype: flashkaa3\n" +
                "                vk.com/the_flash_m\n" +
                "\n" +
                "\n" +
                "        Fridrikh Alexandr\n" +
                "            Contacts:\n" +
                "                E-Mail: alnikf@mail.ru\n" +
                "                Skype: viksolo1\n" +
                "                vk.com/id19328420\n");
    }

    public void restart(View v){
       // startActivity(new Intent(this, MenuActivity.class));
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideStatusBar(this);
    }


}
