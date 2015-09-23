package com.flash.questionnaire.Requests;

import com.flash.questionnaire.Models.Quest;

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

    private String id;
    private String name;
    private String sex;
    private String fio;
    private String prev_quest;
    private String ref;
    private String rev;
    private String mail;
    private String tel;

    public String result;

    private Quest quest;

    public get(final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    URL uri = new URL(url);
                    HttpURLConnection con = (HttpURLConnection) uri.openConnection();
                    readStream(con.getInputStream());
                    parseJSON(getResult());
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void parseJSON(String json){
        JSONObject object;
        try{
            object = new JSONObject(json);
            JSONArray response = object.getJSONArray("response");

            for(int u=0; u<response.length(); u++){
                JSONObject respons = response.getJSONObject(u);

                JSONObject quests = respons.getJSONObject("quest");

                id = quests.getString("id");
                name = quests.getString("name");
                sex = quests.getString("sex");
                fio = quests.getString("fio");
                prev_quest = quests.getString("prev_quest");
                ref = quests.getString("ref");
                rev = quests.getString("rev");
                mail = quests.getString("mail");
                tel = quests.getString("tel");

                quest = new Quest(id, name, sex, fio, prev_quest, ref, rev, mail, tel);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
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
