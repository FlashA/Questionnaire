package com.flash.questionnaire.Design;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.flash.questionnaire.Fragments.FirstFragment;
import com.flash.questionnaire.Fragments.SecondFragment;
import com.flash.questionnaire.Kiosk.StatusBarManager;
import com.flash.questionnaire.R;

/**
 * Created by Anton on 20.09.2015.
 */
public class QuestionnaireActivity extends QuestionnaireApplication {

    private FrameLayout container;

    private FragmentManager fragmentManager;

    private FirstFragment fragmentLogin;

    StatusBarManager statusBarManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);
        container = (FrameLayout) findViewById(R.id.container);



        fragmentManager = getFragmentManager();

        fragmentLogin = new FirstFragment();

        FirstFragment fragment = (FirstFragment) fragmentManager
                .findFragmentById(R.id.fragment_first);

        if (fragment == null) {


            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();
            fragmentTransaction.replace(R.id.container, fragmentLogin);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        } else {
            //    fragment.setMsg("Первый фрагмент уже загружен");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideStatusBar(this);
    }

    public void next(View v){
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.container, new SecondFragment());
        ft.addToBackStack(null);
        ft.commit();
    }





    public void finish(View v){
        startActivity(new Intent(this, ThanksActivity.class));
        finish();
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

}
