package com.example.quyet.qrappmanager.networks.services;

import com.example.quyet.qrappmanager.networks.jsonmodel.CategoryJson;
import com.example.quyet.qrappmanager.networks.jsonmodel.MessageJson;

import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

/**
 * Created by Quyet on 05/11/2017.
 */

public interface CategoryService {
    @GET("category_id/{category_id}")
    Call<CategoryJson> getCategoryWithId(@Path("category_id") String categoryID);

    @GET("category_menu_id/{menu_id}")
    Call<List<CategoryJson>> getCategoryMenuWithId(@Path("menu_id") String categoryID);

    @POST("category")
    Call<CategoryJson> addCategory(@Body RequestBody body);

    @PUT("category_id/{category_id}")
    Call<CategoryJson> editCategoryWithId(@Path("category_id") String categoryID, @Body RequestBody body);

    @DELETE("category_id/{category_id}")
    Call<MessageJson> deleteCategoryWithId(@Path("category_id") String categoryID);

}
