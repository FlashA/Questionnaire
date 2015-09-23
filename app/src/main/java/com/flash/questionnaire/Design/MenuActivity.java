package com.flash.questionnaire.Design;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.flash.questionnaire.Kiosk.StatusBarManager;
import com.flash.questionnaire.R;

/**
 * Created by Anton on 20.09.2015.
 */
public class MenuActivity extends QuestionnaireApplication {


    StatusBarManager statusBarManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
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

/*    protected void hideStatusBar(Context context) {
        statusBarManager = new StatusBarManager(context);
        statusBarManager.hide();
    }

    protected void showStatusBar() {
        statusBarManager.show();
    }


    @Override
    protected void onPause() {
        super.onPause();
        showStatusBar();
    }
*/

/*    private void hideStatusBar() {
        statusBarManager = new StatusBarManager(this);
        statusBarManager.hide();
    }

    private void showStatusBar() {
        statusBarManager.show();
    }
*/


}
