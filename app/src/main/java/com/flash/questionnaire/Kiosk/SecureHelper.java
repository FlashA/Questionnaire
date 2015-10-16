package com.flash.questionnaire.Kiosk;

import android.content.Context;

import com.flash.questionnaire.SQLite.DBDataHelper;
import com.flash.questionnaire.Utils.Constants;

/**
 * Created by Anton on 23.09.2015.
 */
public class SecureHelper {

    Context context;
    DBDataHelper database;
    public SecureHelper(Context context) {
        this.context = context;
        database = new DBDataHelper(context);
    }

    public boolean checkPassword(String pass) {
        if(pass.equals(database.getPassword())) return true;
        else return false;
    }

    public boolean checkDevPassword(String pass) {
        if(pass.equals(Constants.DEV_PASSWORD)) return true;
        else return false;
    }
}
