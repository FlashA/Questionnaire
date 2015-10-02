package com.flash.questionnaire.Fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.flash.questionnaire.Design.List.ListCheckAdapter;
import com.flash.questionnaire.Models.Answers;
import com.flash.questionnaire.R;
import com.flash.questionnaire.SQLite.DBDataHelper;

import java.util.ArrayList;

/**
 * Created by Anton on 22.09.2015.
 */
public class ThirdFragment extends Fragment {

    private Button button_next;

    private TextView textView;

    private DBDataHelper database;

    private int id;

    private Answers answers;

    private ListCheckAdapter adapter;

    private CheckBox checkBox_1;
    private CheckBox checkBox_2;
    private CheckBox checkBox_3;
    private CheckBox checkBox_4;
    private CheckBox checkBox_5;
    private CheckBox checkBox_6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        database = new DBDataHelper(getActivity());




        id = getArguments().getInt("Quest");
        answers = getArguments().getParcelable("answers");
        initButton(view);
        initQuestsList(view);
        setQuestion(view);
        return view;
    }

    private void initButton(View view) {
        button_next = (Button) view.findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (adapter.isChecked()) {
                    // setData();
                    final FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Bundle bundle = new Bundle();
                    bundle.putInt("Quest", id);
                    bundle.putParcelable("answers", adapter.getAnswers());
                    FourthFragment fragment = new FourthFragment();
                    fragment.setArguments(bundle);
                    ft.replace(R.id.container, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                } else {
                    Toast.makeText(getView().getContext(), "Сделайте выбор", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setData(){
        if(checkBox_1.isChecked()) mergeText(checkBox_1);
        if(checkBox_2.isChecked()) mergeText(checkBox_2);
        if(checkBox_3.isChecked()) mergeText(checkBox_3);
        if(checkBox_4.isChecked()) mergeText(checkBox_4);
        if(checkBox_5.isChecked()) mergeText(checkBox_5);
        if(checkBox_6.isChecked()) mergeText(checkBox_6);
    }

    /*private boolean checkChoosing(){
        if(checkBox_1.isChecked() ||
                checkBox_2.isChecked() ||
                checkBox_3.isChecked() ||
                checkBox_4.isChecked() ||
                checkBox_5.isChecked() ||
                checkBox_6.isChecked()) return true;
        else return false;
    }*/

    private boolean checkChoosing(){
        if(checkBox_1.isChecked() ||
                checkBox_2.isChecked() ||
                checkBox_3.isChecked() ||
                checkBox_4.isChecked() ||
                checkBox_5.isChecked() ||
                checkBox_6.isChecked()) return true;
        else return false;
    }

    private void mergeText(CheckBox checkBox){
        if(answers.getPrev_quest() == null) answers.setPrev_quest(checkBox.getText().toString());
        else answers.setPrev_quest(answers.getPrev_quest() + ", " + checkBox.getText().toString());
    }

    private void initCheckBox(View view){
        checkBox_1 = (CheckBox) view.findViewById(R.id.checkBox);
        checkBox_2 = (CheckBox) view.findViewById(R.id.checkBox2);
        checkBox_3 = (CheckBox) view.findViewById(R.id.checkBox3);
        checkBox_4 = (CheckBox) view.findViewById(R.id.checkBox4);
        checkBox_5 = (CheckBox) view.findViewById(R.id.checkBox5);
        checkBox_6 = (CheckBox) view.findViewById(R.id.checkBox6);
        switch (getArguments().getString("QuestName")) {
            case "Искусственный Интеллект":
                checkBox_2.setChecked(true);
                checkBox_2.setVisibility(View.GONE);
                break;
            case "Тайна Перевала Дятлова":
                checkBox_3.setChecked(true);
                checkBox_3.setVisibility(View.GONE);
                break;
            case "Побег Из Супермакса":
                checkBox_4.setChecked(true);
                checkBox_4.setVisibility(View.GONE);
                break;
            case "Погребенные Заживо":
                checkBox_5.setChecked(true);
                checkBox_5.setVisibility(View.GONE);
                break;
            case "Гарри Поттер":
                checkBox_6.setChecked(true);
                checkBox_6.setVisibility(View.GONE);
                break;

        }
    }

    private void setQuestion(View view) {
        textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(database.getQuestions(id, 3));
    }

    private void initQuestsList(View view) {
        ListView listView = (ListView) view.findViewById(R.id.listView);
        listView.setDividerHeight(0);
        ArrayList<String> list = new ArrayList<>();
        list.add("Это мой первый опыт");
        list.addAll(database.getQuests());
   //     if (list.contains(getArguments().getString("QuestName"))) list.remove(getArguments().getString("QuestName"));
        adapter = new ListCheckAdapter(getActivity(), list, answers, getArguments().getString("QuestName"));
        listView.setAdapter(adapter);
    }
}