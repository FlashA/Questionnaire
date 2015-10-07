package com.flash.questionnaire.Requests;

import android.content.Context;

import com.flash.questionnaire.SQLite.DBDataHelper;
import com.flash.questionnaire.Utils.Constants;

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

    private StringBuffer response;

    private DBDataHelper DBHelper;

    public post(final Context context){
        DBHelper = new DBDataHelper(context);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    if(sendPost()) DBHelper.clearTableUsers();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public boolean sendPost() throws Exception  {
        try{
            URL obj = new URL(Constants.API_URL_POST);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String urlParameters = "users=" + URLEncoder.encode(DBHelper.composeJson(), "UTF-8");

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
}
