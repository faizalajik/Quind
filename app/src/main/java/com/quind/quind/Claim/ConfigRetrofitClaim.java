package com.quind.quind.Claim;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofitClaim {
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
    public static Api getInstanceRetrofit() {
        return ConfigRetrofitClaim.retrofitUser().create(Api.class);
    }
}
