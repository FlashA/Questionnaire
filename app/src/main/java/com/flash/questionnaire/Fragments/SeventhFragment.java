package com.flash.questionnaire.Fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.flash.questionnaire.Design.ThanksActivity;
import com.flash.questionnaire.R;

/**
 * Created by Anton on 22.09.2015.
 */
public class SeventhFragment extends Fragment {

    private Button button_next;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_seventh, container, false);

        initButton(view);
        return view;
    }

    private void initButton(View view) {
        button_next = (Button) view.findViewById(R.id.button_next);
        button_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), ThanksActivity.class));
                getActivity().finish();
            }
        });
    }


}