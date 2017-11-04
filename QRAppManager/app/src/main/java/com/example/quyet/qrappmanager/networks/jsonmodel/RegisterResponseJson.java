package com.example.quyet.qrappmanager.networks.jsonmodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Quyet on 05/11/2017.
 */

public class RegisterResponseJson {

    @SerializedName("manager_id")
    private String managerId;
    @SerializedName("manager_name")
    private String managerName;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("token")
    private String token;

    public RegisterResponseJson(String managerId, String managerName, String email, String password, String token) {
        this.managerId = managerId;
        this.managerName = managerName;
        this.email = email;
        this.password = password;
        this.token = token;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
