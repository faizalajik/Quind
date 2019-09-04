package com.quind.quind.LoginUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiServices {
    @FormUrlEncoded
    @POST("user/login.json")
    Call<ResponseLogin> request_login(
            @Field("email") String email,
            @Field("password") String password

    );

    @FormUrlEncoded
    @PUT("user/update/password.json")
    Call<Response> updatePassword(
            @Header("Authorization") String jwt,
            @Field("old_password") String oldPassword,
            @Field("password") String newPassword
    );
}
