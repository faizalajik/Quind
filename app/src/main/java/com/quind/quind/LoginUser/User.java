package com.quind.quind.LoginUser;

public class User {

    private String user_email;
    private String user_password;
    private String jwt;

    public User(String user_email, String user_password, String jwt) {
        this.user_email = user_email;
        this.user_password = user_password;
        this.jwt = jwt;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}




