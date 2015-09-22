package com.flash.questionnaire.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fdh on 20.09.15.
 */
public class PostRequest {

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @SerializedName("response")
    @Expose
    private String reponse;

    @SerializedName("status")
    @Expose
    private String status;
}
