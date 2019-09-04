package com.quind.quind.Home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("user")
    @Expose
    private DataUser user;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public DataUser getUser() {
        return user;
    }

    public void setUser(DataUser user) {
        this.user = user;
    }
}




