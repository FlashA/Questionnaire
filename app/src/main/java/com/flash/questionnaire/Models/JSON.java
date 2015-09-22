package com.flash.questionnaire.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jahfgk on 22.09.2015.
 */

public class JSON {

    @SerializedName("response")
    @Expose
    private List<Quests> questses = new ArrayList<Quests>();

    @SerializedName("status")
    @Expose
    private String status;

    public List<Quests> getQuestses() {
        return questses;
    }

    public void setQuestses(List<Quests> questses) {
        this.questses = questses;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
