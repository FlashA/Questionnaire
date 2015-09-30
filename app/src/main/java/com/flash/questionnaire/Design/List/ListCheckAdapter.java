package com.flash.questionnaire.Design.List;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.flash.questionnaire.Design.MenuActivity;
import com.flash.questionnaire.Design.QuestionnaireActivity;
import com.flash.questionnaire.Models.Answers;
import com.flash.questionnaire.R;

import java.util.ArrayList;

/**
 * Created by Anton on 30.08.2015.
 */
public class ListCheckAdapter extends BaseAdapter  {

    private ArrayList<String> list;
    private LayoutInflater lInflater;
    private Context context;
    private Answers answers;


    public ListCheckAdapter(Context context, ArrayList<String> list, Answers answers, String currentQuest) {
        super();
        this.list = list;
        this.context = context;
        this.answers = answers;

        if (list.contains(currentQuest)) {
            addCurrentQuest(currentQuest);
            list.remove(currentQuest);
            Log.d("my_app", this.answers.getPrev_quest());
        }
        lInflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = lInflater.inflate(R.layout.list_check_item, parent, false);
        }

        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
        checkBox.setText(list.get(position));

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                 //   Toast.makeText(finalView.getContext(), answers.getPrev_quest(), Toast.LENGTH_SHORT).show();
                    mergeText(checkBox);
                    Log.d("my_app", answers.getPrev_quest());
                   // Toast.makeText(finalView.getContext(), answers.getPrev_quest(), Toast.LENGTH_SHORT).show();
                } else {
                    removeText(checkBox);
                    Log.d("my_app", answers.getPrev_quest());
                }
            }
        });
        return view;
    }

    private void removeText(CheckBox checkBox){
        answers.setPrev_quest(answers.getPrev_quest().replace(", "+checkBox.getText().toString(), ""));
    }

    private void mergeText(CheckBox checkBox){
        if(answers.getPrev_quest() == null) answers.setPrev_quest(checkBox.getText().toString());
        else answers.setPrev_quest(answers.getPrev_quest() + ", " + checkBox.getText().toString());
    }

    private void addCurrentQuest(String currentQuest) {
        answers.setPrev_quest(currentQuest);
    }

    public Answers getAnswers() {
        return answers;
    }


}