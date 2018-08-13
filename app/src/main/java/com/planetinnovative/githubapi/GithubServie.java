package com.planetinnovative.githubapi;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GithubServie {

    @GET("/users/{username}")
    Call<GithubResponseModel> getGithubInformation(@Path("username") String uname);

}
