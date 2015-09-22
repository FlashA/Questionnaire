package com.flash.questionnaire.API;

import com.flash.questionnaire.Models.JSON;
import com.flash.questionnaire.Models.PostRequest;
import com.flash.questionnaire.Models.Task;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by fdh on 20.09.15.
 */
public interface API {

    @POST("/api/user.add")
    void addUser(@Field("sex") String sex,
                 @Field("fio") String fio,
                 @Field("prev_quest") String prev,
                 @Field("ref") String ref,
                 @Field("rev") String rev,
                 @Field("mail") String mail,
                 @Field("tel") String tel,
                 Callback<PostRequest> postRequestCallback);

    @POST("/api/user.add")
    void users(@Body Task task, Callback<Task> cb);

    @GET("/api/quest.getList")
    void getQuests(Callback<JSON> cb);
}
