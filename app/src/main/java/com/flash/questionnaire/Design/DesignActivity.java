package com.flash.questionnaire.Design;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;

import com.flash.questionnaire.R;

/**
 * Created by Anton on 20.09.2015.
 */
public class DesignActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(DesignActivity.this, MenuActivity.class));
                finish();
            }
        }.start();

    }
}
