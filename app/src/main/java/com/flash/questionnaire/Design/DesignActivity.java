package com.flash.questionnaire.Design;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.flash.questionnaire.Kiosk.StatusBarManager;
import com.flash.questionnaire.QuestionnaireApplication;
import com.flash.questionnaire.R;
import com.flash.questionnaire.Utils.Constants;
import com.flash.questionnaire.Utils.ContextHelper;

/**
 * Created by Anton on 20.09.2015.
 */
public class DesignActivity extends QuestionnaireApplication {


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
    @Override
    protected void onResume() {
        super.onResume();
        hideStatusBar(this);
    }
}
