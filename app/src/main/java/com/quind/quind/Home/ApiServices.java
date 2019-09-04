package com.quind.quind.Home;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface ApiServices {
    @GET("user")
    Call<User> dataUser(@Header("Authorization") String jwt);

    @GET ("user/policies")
    Call<UserPolicy> userPolicy(@Header("Authorization") String jwt);
}