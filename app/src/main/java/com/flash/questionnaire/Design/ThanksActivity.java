package com.flash.questionnaire.Design;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.flash.questionnaire.R;

/**
 * Created by Anton on 20.09.2015.
 */
public class ThanksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanks);
    }
    public void restart(View v){

        finish();
    }
}
