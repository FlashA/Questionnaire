package com.flash.questionnaire.Models;

/**
 * Created by Jahfgk on 23.09.2015.
 */
public class Quest {
    private String id;
    private String name;
    private String sex;
    private String fio;
    private String prev_quest;
    private String ref;
    private String rev;
    private String mail;
    private String tel;

    public Quest(String id, String name,
                 String sex, String fio,
                 String prev_quest, String ref,
                 String rev, String mail, String tel){
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.fio = fio;
        this.prev_quest = prev_quest;
        this.ref = ref;
        this.rev = rev;
        this.mail = mail;
        this.tel = tel;
    }

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
}
