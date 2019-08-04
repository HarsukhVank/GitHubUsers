package com.harsukh.githubusers.network;

import com.harsukh.githubusers.models.GitUsersResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by DELL on 8/2/2019.
 */

public interface ApiInterface {

     @GET("/search/users")
     Call<GitUsersResponseModel> getUsers(@Query("q") String q);
}
