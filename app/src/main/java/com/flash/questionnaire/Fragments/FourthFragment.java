package com.flash.questionnaire.Fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.flash.questionnaire.Models.Answers;
import com.flash.questionnaire.R;
import com.flash.questionnaire.SQLite.DBDataHelper;

/**
 * Created by Anton on 22.09.2015.
 */
public class FourthFragment extends Fragment {

    private Button button_next;

    private TextView textView;

    private DBDataHelper database;

    private int id;

    private Answers answers;

    private CheckBox checkBox_1;
    private CheckBox checkBox_2;
    private CheckBox checkBox_3;
    private CheckBox checkBox_4;
    private CheckBox checkBox_5;
    private CheckBox checkBox_6;
    private CheckBox checkBox_7;
    private CheckBox checkBox_8;
    private CheckBox checkBox_9;

    private EditText editText_another;

    private int i;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fourth, container, false);
        database = new DBDataHelper(getActivity());
        initButton(view);
        initCheckBox(view);
        initEditText(view);


        id = getArguments().getInt("Quest");
        answers = getArguments().getParcelable("answers");

        setQuestion(view);
        return view;
    }

    private void initButton(View view) {
        button_next = (Button) view.findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkChoosing()) {
                    setData();
                    final FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Bundle bundle = new Bundle();
                    bundle.putInt("Quest", id);
                    bundle.putParcelable("answers", answers);
                    FifthFragment fragment = new FifthFragment();
                    fragment.setArguments(bundle);
                    hideKeyboard();
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
        if(checkBox_7.isChecked()) mergeText(checkBox_7);
        if(checkBox_8.isChecked()) mergeText(checkBox_8);
        if(checkBox_9.isChecked()) mergeText(checkBox_9);
        if(!editText_another.getText().toString().equals("")) mergeTextEditText(editText_another);
    }

    private boolean checkChoosing(){
        if(checkBox_1.isChecked() ||
                checkBox_2.isChecked() ||
                checkBox_3.isChecked() ||
                checkBox_4.isChecked() ||
                checkBox_5.isChecked() ||
                checkBox_6.isChecked() ||
                checkBox_7.isChecked() ||
                checkBox_8.isChecked() ||
                checkBox_9.isChecked() ||
                !editText_another.getText().toString().equals("")) return true;
        else return false;
    }

    private void mergeText(CheckBox checkBox){
        if(answers.getRef() == null) answers.setRef(checkBox.getText().toString());
        else answers.setRef(answers.getRef() + ";" + checkBox.getText().toString());
    }

    private void mergeTextEditText(EditText editText){
        if(answers.getRef() == null) answers.setRef(editText.getText().toString());
        else answers.setRef(answers.getRef() + ";" + editText.getText().toString());
    }

    private void initEditText(View view){
        editText_another = (EditText) view.findViewById(R.id.editText_another);
    }

    private void initCheckBox(View view){
        checkBox_1 = (CheckBox) view.findViewById(R.id.checkBox);
        checkBox_2 = (CheckBox) view.findViewById(R.id.checkBox2);
        checkBox_3 = (CheckBox) view.findViewById(R.id.checkBox3);
        checkBox_4 = (CheckBox) view.findViewById(R.id.checkBox4);
        checkBox_5 = (CheckBox) view.findViewById(R.id.checkBox5);
        checkBox_6 = (CheckBox) view.findViewById(R.id.checkBox6);
        checkBox_7 = (CheckBox) view.findViewById(R.id.checkBox7);
        checkBox_8 = (CheckBox) view.findViewById(R.id.checkBox8);
        checkBox_9 = (CheckBox) view.findViewById(R.id.checkBox9);
    }

    private void setQuestion(View view) {
        textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(database.getQuestions(id, 4));
    }
    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}