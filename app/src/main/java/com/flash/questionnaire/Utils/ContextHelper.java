package com.flash.questionnaire.Utils;

import android.content.Context;

/**
 * Created by Anton on 23.09.2015.
 */
public class ContextHelper {

    public static ContextHelper instance = new ContextHelper();
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private Context context;


}
