package com.flash.questionnaire.Fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.flash.questionnaire.R;
import com.flash.questionnaire.SQLite.DBDataHelper;

import java.util.ArrayList;

/**
 * Created by Anton on 22.09.2015.
 */
public class FirstFragment extends Fragment {

    private Button button_next;

    private TextView textView;

    private DBDataHelper database;

    private int id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
        database = new DBDataHelper(getActivity());
        initButton(view);


        id = getArguments().getInt("Quest");
        setQuestion(view);
        return view;
    }

    private void initButton(View view) {
        button_next = (Button) view.findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putInt("Quest", id);
                SecondFragment fragment = new SecondFragment();
                fragment.setArguments(bundle);
                ft.replace(R.id.container, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }

    private void setQuestion(View view) {
        textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(database.getQuestions(id, 1));
     //   ArrayList<String> list = new ArrayList<>(database.getQuestions1());

     //   for(int position = 0; position<list.size(); position++) {
     //       Log.d("my_app", list.get(position));
     //   }
    }


}