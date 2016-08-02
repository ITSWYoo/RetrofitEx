package com.example.yoo.retrofitex;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.Path;

/**
 * Created by Yoo on 2016-08-02.
 */
public interface GitHubService {

    public static final String URL = "https://api.github.com/";

    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user")String user);
}
