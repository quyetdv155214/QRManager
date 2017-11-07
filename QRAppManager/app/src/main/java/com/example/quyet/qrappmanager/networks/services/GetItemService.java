package com.example.quyet.qrappmanager.networks.services;

import com.example.quyet.qrappmanager.networks.jsonmodel.ItemJson;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Khuong Duy on 11/5/2017.
 */

public interface GetItemService {
    @GET("item_with_cateid/{cate_id}")
    Call<List<ItemJson>> getMenuWithId(@Path("cate_id")String cate_id);
}
