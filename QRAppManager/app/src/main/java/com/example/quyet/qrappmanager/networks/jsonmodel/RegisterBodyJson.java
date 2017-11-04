package com.example.quyet.qrappmanager.networks.jsonmodel;

import com.example.quyet.qrappmanager.ultil.Utils;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Quyet on 05/11/2017.
 */

public class RegisterBodyJson {
    @SerializedName("manager_name")
    private String managerName;
    @SerializedName("manager_id")
    private String managerId;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;

    public RegisterBodyJson(String managerName, String email, String password) {
        this.managerName = managerName;
        this.managerId = Utils.instance.getUUID();
        this.email = email;
        this.password = password;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
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
}
