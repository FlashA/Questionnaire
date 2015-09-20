package com.flash.questionnaire;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

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
