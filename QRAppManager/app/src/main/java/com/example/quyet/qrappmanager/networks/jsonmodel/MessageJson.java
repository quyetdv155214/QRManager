package com.example.quyet.qrappmanager.networks.jsonmodel;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Khuong Duy on 11/7/2017.
 */

public class MessageJson {
    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
