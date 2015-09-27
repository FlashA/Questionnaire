package com.flash.questionnaire.Design.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.flash.questionnaire.Design.MenuActivity;
import com.flash.questionnaire.Design.QuestionnaireActivity;
import com.flash.questionnaire.R;
import com.flash.questionnaire.SQLite.DBDataHelper;

import java.util.ArrayList;

/**
 * Created by Anton on 30.08.2015.
 */
public class ListAdapter extends BaseAdapter  {

    private ArrayList<String> list;
    private LayoutInflater lInflater;
    private Context context;


    public ListAdapter(Context context, ArrayList<String> list) {
        super();
        this.list = list;
        this.context = context;
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
            view = lInflater.inflate(R.layout.list_item, parent, false);
        }


    //    ((TextView) view.findViewById(R.id.textView)).setText("№ вопроса: " + list.get(position).getQuestion() + " Ответ: " + list.get(position).getAnswer());

        final Button button = (Button) view.findViewById(R.id.button);
        button.setText(list.get(position));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, QuestionnaireActivity.class);
                intent.putExtra("QuestName", button.getText().toString());
                context.startActivity(intent);
                ((MenuActivity)context).finish();
            }
        });




        return view;
    }

}