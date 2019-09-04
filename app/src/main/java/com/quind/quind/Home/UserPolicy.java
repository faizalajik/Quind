package com.quind.quind.Home;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserPolicy {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("policies")
    @Expose
    private List<Policy> policies = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Policy> getPolicies() {
        return policies;
    }

    public void setPolicies(List<Policy> policies) {
        this.policies = policies;
    }
}
