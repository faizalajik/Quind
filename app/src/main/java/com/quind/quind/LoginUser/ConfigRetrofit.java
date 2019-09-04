package com.quind.quind.LoginUser;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConfigRetrofit {
    public static Retrofit retrofitget() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor() ;
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY) ;

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()  ;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://quind-api.herokuapp.com/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        return retrofit;
    }

    public static ApiServices getInstanceRetrofit() {
        return ConfigRetrofit.retrofitget().create(ApiServices.class);
    }
}
