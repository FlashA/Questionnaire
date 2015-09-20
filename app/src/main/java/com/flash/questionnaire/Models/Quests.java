package com.flash.questionnaire.Models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit.client.Response;

/**
 * Created by fdh on 20.09.15.
 */
public class Quests {
    public List<Response> getResponse() {
        return response;
    }

    public void setResponse(List<Response> response) {
        this.response = response;
    }

    @SerializedName("response")
    List<Response> response;
}
