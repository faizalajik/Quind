package com.quind.quind.Claim;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface Api {

    @GET("user/claims")
    Call<ResponseClaim> dataClaim(@Header("Authorization") String jwt);


}
