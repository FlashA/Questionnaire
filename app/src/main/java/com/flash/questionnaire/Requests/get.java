package com.flash.questionnaire.Requests;

import android.content.Context;

import com.flash.questionnaire.Models.Quest;
import com.flash.questionnaire.SQLite.DBDataHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Jahfgk on 23.09.2015.
 */
public class get {

    public String result;

    private Quest quest;
    private DBDataHelper DBHelper;

    public get(final String url, final Context context){
        DBHelper = new DBDataHelper(context);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    URL uri = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) uri.openConnection();
                    readStream(con.getInputStream());
                    if(getStatus(getResult())){
                        DBHelper.clearTableQuests();
                        DBHelper.clearTableIssue();
                    }
                    parseJSON(getResult());
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void parseJSON(String json){
        JSONObject object;
        try {
            object = new JSONObject(json);
            JSONArray response = object.getJSONArray("response");

            for(int u=0; u < response.length(); u++) {
                quest = new Quest();
                JSONObject respons = response.getJSONObject(u);
                JSONObject quests = respons.getJSONObject("quest");

                quest.setId(quests.getString("id"));
                quest.setName(quests.getString("name"));
                quest.setSex(quests.getString("sex"));
                quest.setFio(quests.getString("fio"));
                quest.setPrev_quest(quests.getString("prev_quest"));
                quest.setRef(quests.getString("ref"));
                quest.setRev(quests.getString("rev"));
                quest.setMail(quests.getString("mail"));
                quest.setTel(quests.getString("tel"));

                DBHelper.addQuests(quest);

                DBHelper.addIssue(quest.getId(), quest.getSex());
                DBHelper.addIssue(quest.getId(), quest.getFio());
                DBHelper.addIssue(quest.getId(), quest.getPrev_quest());
                DBHelper.addIssue(quest.getId(), quest.getRef());
                DBHelper.addIssue(quest.getId(), quest.getRev());
                DBHelper.addIssue(quest.getId(), quest.getMail());
                DBHelper.addIssue(quest.getId(), quest.getTel());
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    public boolean getStatus(String json){
        JSONObject object = null;
        boolean status = false;
        try{
            object = new JSONObject(json);
            if(object.getString("status").equals("success")) status = true;
        } catch (JSONException e){
            status = false;
            e.printStackTrace();
        }
        return status;
    }

    private void readStream(InputStream in) throws IOException{
        BufferedReader r = new BufferedReader(new InputStreamReader(in));
        StringBuilder total = new StringBuilder();
        String line;
        while((line = r.readLine()) != null){
            total.append(line);
        }
        result = total.toString();
    }

    public String getResult(){
        return result;
    }
}
