package com.quind.quind.Claim;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseClaim {
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("claims")
    @Expose
    private List<Claim> claims;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Claim> getClaims() {
        return claims;
    }

    public void setClaims(List<Claim> claims) {
        this.claims = claims;
    }
}
