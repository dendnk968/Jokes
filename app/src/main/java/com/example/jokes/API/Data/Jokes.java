package com.example.jokes.API.Data;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class Jokes implements Serializable {
    @SerializedName("type")
    String result;
    @SerializedName("value")
    ArrayList<Joke> jokes;

    public Jokes(ArrayList<Joke> jokes) {
        this.jokes = jokes;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public ArrayList<Joke> getJokes() {
        return jokes;
    }

    public void setJokes(ArrayList<Joke> jokes) {
        this.jokes = jokes;
    }
}
