package com.example.jokes.API;

import com.example.jokes.API.Data.Jokes;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface APIJokes {
    @GET("jokes/random/{count}")
    Call<Jokes> getJokesCount(@Path("count") int count);
}
