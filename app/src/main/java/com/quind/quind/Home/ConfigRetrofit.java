package com.quind.quind.Home;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {

    private static Retrofit retrofit;
    public static Retrofit retrofitUser () {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        retrofit = new Retrofit.Builder().baseUrl("https://quind-api.herokuapp.com/v1/").
                addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }
    public static ApiServices getInstanceRetrofit() {
        return ConfigRetrofit.retrofitUser().create(ApiServices.class);
    }
}
