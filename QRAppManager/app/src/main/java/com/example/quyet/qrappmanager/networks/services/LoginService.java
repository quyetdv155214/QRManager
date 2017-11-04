package com.example.quyet.qrappmanager.networks.services;

import com.example.quyet.qrappmanager.networks.jsonmodel.LoginBodyJson;
import com.example.quyet.qrappmanager.networks.jsonmodel.LoginResponseJson;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Quyet on 05/11/2017.
 */

public interface LoginService {
    @POST("mlogin")
    Call<LoginResponseJson> login(@Body RequestBody body);
}
