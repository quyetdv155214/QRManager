package com.example.quyet.qrappmanager.networks.services;

import com.example.quyet.qrappmanager.networks.jsonmodel.MenuJson;
import com.example.quyet.qrappmanager.networks.jsonmodel.MenuResponseJson;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Quyet on 05/11/2017.
 */

public interface MenuService {
    @POST("menu")
    Call<MenuResponseJson> addMenu(@Body RequestBody body);
    @GET("menu_manager/{manager_id}")
    Call<MenuJson> getMenu(@Path("manager_id") String managerID);
}
