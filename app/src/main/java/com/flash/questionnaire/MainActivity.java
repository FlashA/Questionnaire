package com.flash.questionnaire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.flash.questionnaire.Design.DesignActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void func (View v) {
        startActivity(new Intent(this, FuncActivity.class));
    }

    public void design (View v) {
        startActivity(new Intent(this, DesignActivity.class));
    }


}
