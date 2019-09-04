package com.quind.quind.LoginUser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseLogin {

    @SerializedName("message")
    @Expose
    private String status;
    @SerializedName("jwt")
    @Expose
    private String jwt;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    ArrayList<User> user = new ArrayList();
}
