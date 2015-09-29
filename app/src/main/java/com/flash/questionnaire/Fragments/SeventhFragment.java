package com.flash.questionnaire.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.flash.questionnaire.Design.ThanksActivity;
import com.flash.questionnaire.Models.Answers;
import com.flash.questionnaire.R;
import com.flash.questionnaire.SQLite.DBDataHelper;

/**
 * Created by Anton on 22.09.2015.
 */
public class SeventhFragment extends Fragment {

    private Button button_next;

    private TextView textView;

    private DBDataHelper database;

    private int id;

    private Answers answers;

    private EditText editText_f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seventh, container, false);
        database = new DBDataHelper(getActivity());
        initButton(view);
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
                setText();
                Intent intent = new Intent(getActivity(), ThanksActivity.class);
                intent.putExtra("answer", answers);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }

    private void initEditText(View view){
        editText_f = (EditText) view.findViewById(R.id.editText_f);
    }

    private void setText(){
        if(!getText().equals("")) answers.setTel(editText_f.getText().toString());
    }

    private String getText(){
        return editText_f.getText().toString();
    }

    private void setQuestion(View view) {
        textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(database.getQuestions(id, 7));
    }

}