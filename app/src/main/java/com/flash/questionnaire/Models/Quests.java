package com.flash.questionnaire.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fdh on 20.09.15.
 */
public class Quests {

    @SerializedName("quest")
    @Expose
    private QuestsInfo questsInfo;

    public QuestsInfo getQuestsInfo() {
        return questsInfo;
    }

    public void setQuestsInfo(QuestsInfo questsInfo) {
        this.questsInfo = questsInfo;
    }
}
