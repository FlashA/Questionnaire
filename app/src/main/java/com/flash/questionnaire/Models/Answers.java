package com.flash.questionnaire.Models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by fdh on 28.09.15.
 */
public class Answers implements Parcelable {

    private String id;
    private String sex;
    private String fio;
    private String ref;
    private String rev;
    private String mail;
    private String tel;

    public Answers(){}

    protected Answers(Parcel in) {
        id = in.readString();
        sex = in.readString();
        fio = in.readString();
        ref = in.readString();
        rev = in.readString();
        mail = in.readString();
        tel = in.readString();
    }

    public static final Creator<Answers> CREATOR = new Creator<Answers>() {
        @Override
        public Answers createFromParcel(Parcel in) {
            return new Answers(in);
        }

        @Override
        public Answers[] newArray(int size) {
            return new Answers[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(sex);
        dest.writeString(fio);
        dest.writeString(ref);
        dest.writeString(rev);
        dest.writeString(mail);
        dest.writeString(tel);
    }
}
