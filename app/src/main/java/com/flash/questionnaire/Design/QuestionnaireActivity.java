package com.flash.questionnaire.Design;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.flash.questionnaire.R;

/**
 * Created by Anton on 20.09.2015.
 */
public class QuestionnaireActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_first);
    }

    public void finish(View v){
        startActivity(new Intent(this, ThanksActivity.class));
        finish();
    }
}
