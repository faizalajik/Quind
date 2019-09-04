package com.quind.quind.LoginUser;

import com.google.gson.annotations.SerializedName;

public class Response{

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;

    public void setMessage(String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    @Override
    public String toString(){
        return
                "Response{" +
                        "message = '" + message + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}