package com.flash.questionnaire.Design;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.flash.questionnaire.Fragments.FirstFragment;
import com.flash.questionnaire.Fragments.SecondFragment;
import com.flash.questionnaire.Models.Answers;
import com.flash.questionnaire.R;
import com.flash.questionnaire.SQLite.DBDataHelper;

/**
 * Created by Anton on 20.09.2015.
 */
public class QuestionnaireActivity extends QuestionnaireApplication {

    private FrameLayout container;

    private FragmentManager fragmentManager;

    private FirstFragment fragmentLogin;

    private DBDataHelper database;

    private Answers answers;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questionnaire);

        answers = new Answers();

        database = new DBDataHelper(this);
        container = (FrameLayout) findViewById(R.id.container);

        try {
            switch (getIntent().getExtras().getString("QuestName")) {
            case "Искусственный Интеллект":
                container.setBackgroundResource(R.drawable.iskusstvenny_intellekt2);
                break;
            case "Тайна Перевала Дятлова":
                container.setBackgroundResource(R.drawable.dyatlov2);
                break;
            case "Побег Из Супермакса":
                container.setBackgroundResource(R.drawable.supermax2);
                break;
            case "Погребенные Заживо":
                container.setBackgroundResource(R.drawable.pogrebennye_zazhivo2);
                break;
            case "Гарри Поттер":
                container.setBackgroundResource(R.drawable.garri_potter2);
                break;

            }
        } catch (Exception ex ) {

        }
     //   container.setBackgroundResource(R.drawable.dyatlov2);

        fragmentManager = getFragmentManager();

        fragmentLogin = new FirstFragment();

        FirstFragment fragment = (FirstFragment) fragmentManager
                .findFragmentById(R.id.fragment_first);

        if (fragment == null) {


            FragmentTransaction fragmentTransaction = fragmentManager
                    .beginTransaction();
            Bundle bundle = new Bundle();
            bundle.putInt("Quest", database.getQuestId(getIntent().getExtras().getString("QuestName")));
            bundle.putString("QuestName", getIntent().getExtras().getString("QuestName"));
            bundle.putParcelable("answers", answers);
            // set Fragmentclass Arguments
            fragmentLogin.setArguments(bundle);

           // getIntent().getExtras().getString("QuestName");
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

}
