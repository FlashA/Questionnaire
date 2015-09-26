package com.flash.questionnaire.Requests;

import android.content.Context;

import com.flash.questionnaire.SQLite.DBDataHelper;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Jahfgk on 23.09.2015.
 */
public class post {

    private String sex;
    private String fio;
    private String prev_quest;
    private String ref;
    private String rev;
    private String mail;
    private String tel;

    private String url;

    private StringBuffer response;

    private DBDataHelper DBHelper;

    public post(String url, String sex,
                final String fio, String prev_quest,
                String ref, String rev,
                final String mail, final String tel, final Context context){
        this.url = url;
        this.sex = sex;
        this.fio = fio;
        this.prev_quest = prev_quest;
        this.ref = ref;
        this.rev = rev;
        this.mail = mail;
        this.tel = tel;
        DBHelper = new DBDataHelper(context);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if(sendPost() && getStatus(response.toString())){
                        DBHelper.clearRecordUser(mail);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public boolean sendPost() throws Exception  {
        try{
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String urlParameters = "sex=" + URLEncoder.encode(sex, "UTF-8") +
                    "&fio=" + URLEncoder.encode(fio, "UTF-8") +
                    "&prev_quest="+ URLEncoder.encode(prev_quest, "UTF-8") +
                    "&ref="+ URLEncoder.encode(ref, "UTF-8") +
                    "&rev=" + URLEncoder.encode(rev, "UTF-8") +
                    "&mail=" + URLEncoder.encode(mail, "UTF-8") +
                    "&tel=" + URLEncoder.encode(tel, "UTF-8");

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return true;
        } catch (IOException e){
            e.printStackTrace();
            return false;
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
}
