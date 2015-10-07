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
import android.widget.EditText;
import android.widget.TextView;

import com.flash.questionnaire.Models.Answers;
import com.flash.questionnaire.R;
import com.flash.questionnaire.SQLite.DBDataHelper;

/**
 * Created by Anton on 22.09.2015.
 */
public class Fourth1Fragment extends Fragment {

    private Button button_next;

    private TextView textView;

    private DBDataHelper database;

    private int id;

    private Answers answers;

    private EditText editText_f;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fourth1, container, false);
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
             /*
                Intent intent = new Intent(getActivity(), ThanksActivity.class);
                intent.putExtra("answer", answers);
                hideKeyboard();
                startActivity(intent);
                getActivity().finish();*/
                setText();
                    final FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Bundle bundle = new Bundle();
                    bundle.putInt("Quest", id);
                    bundle.putParcelable("answers", answers);
                    bundle.putString("QuestName", getArguments().getString("QuestName"));
                    Fifth5Fragment fragment = new Fifth5Fragment();
                    fragment.setArguments(bundle);
                    hideKeyboard();
                    ft.replace(R.id.container, fragment);
                    ft.addToBackStack(null);
                    ft.commit();


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
        textView.setText(database.getQuestions(id, 6));
    }
    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}