package com.flash.questionnaire.API;

import com.flash.questionnaire.Models.Quests;
import com.flash.questionnaire.Models.ResponseUser;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by fdh on 20.09.15.
 */
public interface API {
    @FormUrlEncoded
    @POST("/api/user.add")
    void addUser(@Field("sex") String sex,
                 @Field("fio") String fio,
                 @Field("prev_quest") String prev,
                 @Field("ref") String ref,
                 @Field("rev") String rev,
                 @Field("mail") String mail,
                 @Field("tel") String tel,
                 Callback<ResponseUser> responseUserCallback);

    @GET("/api/quest.getList")
    void getQuests(Callback<Quests> cb);
}
