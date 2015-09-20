package com.flash.questionnaire.Models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fdh on 20.09.15.
 */
public class Response {

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPrev_quest() {
        return prev_quest;
    }

    public void setPrev_quest(String prev_quest) {
        this.prev_quest = prev_quest;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getRev() {
        return rev;
    }

    public void setRev(String rev) {
        this.rev = rev;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("sex")
    String sex;

    @SerializedName("fio")
    String fio;

    @SerializedName("prev_quest")
    String prev_quest;

    @SerializedName("ref")
    String ref;

    @SerializedName("rev")
    String rev;

    @SerializedName("mail")
    String mail;

    @SerializedName("tel")
    String tel;
}
