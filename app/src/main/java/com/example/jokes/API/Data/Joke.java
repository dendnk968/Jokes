package com.example.jokes.API.Data;

import com.google.gson.annotations.SerializedName;

public class Joke {
    @SerializedName("id")
    int id;
    @SerializedName("joke")
    String joke;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}
