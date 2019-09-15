package com.example.jokes.API;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIHelper {
    private static APIHelper apiHelper;
    private static String BASE_URL = "https://api.icndb.com/";
    private Retrofit retrofit;

    private APIHelper() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client.build())
                .build();

    }

    public static APIJokes getInstance() {
        if (apiHelper == null)
            apiHelper = new APIHelper();
        return apiHelper.retrofit.create(APIJokes.class);
    }
}
