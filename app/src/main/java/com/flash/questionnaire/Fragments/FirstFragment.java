package com.flash.questionnaire.Fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.flash.questionnaire.Models.Answers;
import com.flash.questionnaire.R;
import com.flash.questionnaire.SQLite.DBDataHelper;

/**
 * Created by Anton on 22.09.2015.
 */
public class FirstFragment extends Fragment {

    private Button button_next;
    private Button button_m;
    private Button button_f;

    private TextView textView;

    private DBDataHelper database;

    private int id;

    private Answers answers;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        database = new DBDataHelper(getActivity());
        initButton(view);


        id = getArguments().getInt("Quest");
        answers = getArguments().getParcelable("answers");
        setQuestion(view);
        return view;
    }

    private void initButton(View view) {
        button_f = (Button) view.findViewById(R.id.button_f);
        button_f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answers.setSex("Ж");
                goFragment();
            }
        });

        button_m = (Button) view.findViewById(R.id.button_m);
        button_m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                answers.setSex("М");
                goFragment();
            }
        });
    }

    public void goFragment(){
        final FragmentTransaction ft = getFragmentManager().beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putInt("Quest", id);
        bundle.putParcelable("answers", answers);
        SecondFragment fragment = new SecondFragment();
        fragment.setArguments(bundle);
        ft.replace(R.id.container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    private void setQuestion(View view) {
        textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(database.getQuestions(id, 1));
    }
}