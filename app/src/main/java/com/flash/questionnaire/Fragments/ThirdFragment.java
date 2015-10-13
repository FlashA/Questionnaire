package com.flash.questionnaire.Fragments;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.flash.questionnaire.Models.Answers;
import com.flash.questionnaire.R;
import com.flash.questionnaire.SQLite.DBDataHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anton on 22.09.2015.
 */
public class ThirdFragment extends Fragment {

    private Button button_next;

    private TextView textView;

    private DBDataHelper database;

    private int id;

    private Answers answers;

    private boolean hidden = false;

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


    private int checkedNumber = 0;

    private List<String> listForResult=new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_third, container, false);
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
                if (checkSelected()) {
                    if (!editText_another.getText().toString().equals(""))
                        mergeTextEditText(editText_another);
                    setData();
                    final FragmentTransaction ft = getFragmentManager().beginTransaction();
                    Bundle bundle = new Bundle();
                    bundle.putInt("Quest", id);
                    bundle.putParcelable("answers", answers);
                    FourthFragment fragment = new FourthFragment();
                    fragment.setArguments(bundle);
                    hideKeyboard();
                    ft.replace(R.id.container, fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                } else {
                    Log.d("my_app", "Вернули: " + checkSelected());
                    Toast.makeText(getView().getContext(), "Сделайте выбор", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setData(){
        answers.setRef(listToString());

    }

    private boolean checkSelected(){
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

  /*  private void mergeText(CheckBox checkBox){
        if(answers.getRef() == null || answers.getRef().equals("")) answers.setRef(checkBox.getText().toString());
        else answers.setRef(answers.getRef() + ";" + checkBox.getText().toString());
    }*/

    private void mergeText(CheckBox checkBox){
        if (!listForResult.contains(checkBox.getText().toString())) {
            listForResult.add(checkBox.getText().toString());
        }
    }

 /*   private void removeText(CheckBox checkBox){
        String forReplace = answers.getRef();
        String replaced;
        if (checkedNumber==0) {
            answers.setRef(null);
        } else if (checkedNumber==1) {
            replaced=forReplace.replace(checkBox.getText().toString(), "");
            answers.setRef(replaced);
        } else {
          //  answers.setRef(answers.getRef().replace(";"+checkBox.getText().toString(), ""));
            replaced=forReplace.replace(";"+checkBox.getText().toString(), "");
            answers.setRef(replaced);
        }

    }*/

    private void removeText(CheckBox checkBox){
        listForResult.remove(checkBox.getText().toString());
    }

    private String listToString() {
        String result = "";
        for (String str : listForResult) {
            result+= str+";";
        }
       // int index = result.lastIndexOf(";");
      //  result = result.;
        return removeLastChar(result);
    }
    private void mergeTextEditText(EditText editText){
     //   if(answers.getRef() == null) answers.setRef(editText.getText().toString());
   //     else answers.setRef(answers.getRef() + ";" + editText.getText().toString());
        if (!listForResult.contains(editText.getText().toString())) {
            listForResult.add(editText.getText().toString());
        }
    }

    public String removeLastChar(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.substring(0, s.length()-1);
    }
    private void initEditText(final View view){
        editText_another = (EditText) view.findViewById(R.id.editText_another);
   /*     editText_another.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean b) {
                LinearLayout layout=(LinearLayout) view.findViewById(R.id.linearLayout);
                if (b) {
                    layout.setVisibility(LinearLayout.GONE);
                } else {
                    layout.setVisibility(LinearLayout.VISIBLE);
                }
            }
        });*/
        final LinearLayout layout = (LinearLayout) view.findViewById(R.id.linearLayout);
        editText_another.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (hidden) {
                    hidden = false;
                    layout.setVisibility(LinearLayout.VISIBLE);
                } else {
                    hidden = true;
                    layout.setVisibility(LinearLayout.GONE);
                }
            }
        });
    }

    private void initCheckBox(View view){
        checkBox_1 = (CheckBox) view.findViewById(R.id.checkBox);
        addTextManager(checkBox_1);
        checkBox_2 = (CheckBox) view.findViewById(R.id.checkBox2);
        addTextManager(checkBox_2);
        checkBox_3 = (CheckBox) view.findViewById(R.id.checkBox3);
        addTextManager(checkBox_3);
        checkBox_4 = (CheckBox) view.findViewById(R.id.checkBox4);
        addTextManager(checkBox_4);
        checkBox_5 = (CheckBox) view.findViewById(R.id.checkBox5);
        addTextManager(checkBox_5);
        checkBox_6 = (CheckBox) view.findViewById(R.id.checkBox6);
        addTextManager(checkBox_6);
        checkBox_7 = (CheckBox) view.findViewById(R.id.checkBox7);
        addTextManager(checkBox_7);
        checkBox_8 = (CheckBox) view.findViewById(R.id.checkBox8);
        addTextManager(checkBox_8);
        checkBox_9 = (CheckBox) view.findViewById(R.id.checkBox9);
        addTextManager(checkBox_9);

    }

    private void addTextManager(final CheckBox checkBox) {
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    //   Toast.makeText(finalView.getContext(), answers.getPrev_quest(), Toast.LENGTH_SHORT).show();
                    mergeText(checkBox);
              //      Log.d("my_app", answers.getRef());
                    checkedNumber++;
                    Log.d("my_app", Integer.toString(checkedNumber));

                    // Toast.makeText(finalView.getContext(), answers.getPrev_quest(), Toast.LENGTH_SHORT).show();
                } else {
                    checkedNumber--;
                    removeText(checkBox);

                    Log.d("my_app", Integer.toString(checkedNumber));


                  //  Log.d("my_app", answers.getRef());

                }
                try{
                    Log.d("my_app", listToString());
                }catch (NullPointerException ex) {
                    Log.d("my_app", "listToString(): "+ex);
                }
            }
        });

    }
    private void setQuestion(View view) {
        textView = (TextView) view.findViewById(R.id.textView);
        textView.setText(database.getQuestions(id, 3));
    }
    private void hideKeyboard() {
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}