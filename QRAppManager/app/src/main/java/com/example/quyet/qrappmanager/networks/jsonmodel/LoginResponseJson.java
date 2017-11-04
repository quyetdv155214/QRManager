package com.example.quyet.qrappmanager.networks.jsonmodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Quyet on 05/11/2017.
 */

public class LoginResponseJson {
    @SerializedName("manager id")
    private String managerId;

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    @Override
    public String toString() {
        return "LoginResponseJson{" +
                "managerId='" + managerId + '\'' +
                '}';
    }
}
