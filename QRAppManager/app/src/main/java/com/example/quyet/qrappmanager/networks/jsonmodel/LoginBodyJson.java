package com.example.quyet.qrappmanager.networks.jsonmodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Quyet on 04/11/2017.
 */

public class LoginBodyJson {
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String passWord;


    public LoginBodyJson(String email, String passWord) {
        this.email = email;
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }


    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public String toString() {
        return "LoginBodyJson{" +
                "email='" + email + '\'' +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
