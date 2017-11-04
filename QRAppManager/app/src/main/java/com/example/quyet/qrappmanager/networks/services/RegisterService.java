package com.example.quyet.qrappmanager.networks.services;

import com.example.quyet.qrappmanager.networks.jsonmodel.RegisterResponseJson;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Quyet on 05/11/2017.
 */

public interface RegisterService {
    @POST("mRegister")
    Call<RegisterResponseJson> register(@Body RequestBody body);
}
