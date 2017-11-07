package com.example.quyet.qrappmanager.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.quyet.qrappmanager.BR;
import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.databinding.ActivityAddCategoryBinding;
import com.example.quyet.qrappmanager.model.MenuCategory;
import com.example.quyet.qrappmanager.networks.NetContext;
import com.example.quyet.qrappmanager.networks.jsonmodel.CategoryJson;
import com.example.quyet.qrappmanager.networks.jsonmodel.ItemJson;
import com.example.quyet.qrappmanager.networks.services.CategoryService;
import com.example.quyet.qrappmanager.ultil.Constant;
import com.example.quyet.qrappmanager.ultil.Utils;
import com.example.quyet.qrappmanager.viewmodel.ActivityAddCategoryViewModel;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCategoryActivity extends BaseActivity<ActivityAddCategoryBinding, ActivityAddCategoryViewModel> implements View.OnClickListener {
    public static final MediaType JSON = MediaType.parse("application/json");
    private static final String TAG = "Add Categoty";
    private String menuId;

    @Override
    public int getLayoutId() {
        return R.layout.activity_add_category;
    }

    @Override
    public ActivityAddCategoryViewModel setViewModel() {
        return new ActivityAddCategoryViewModel();
    }

    @Override
    public int getVariableId() {
        return BR.viewModel;
    }

    @Override
    public void onCreateActivity() {
        init();
        menuId = getIntent().getStringExtra(Constant.PASS_MENUID);
    }

    private void init() {
        getSupportActionBar().hide();
        setStatusBarTranslucent(true);
        setEvent();
        Log.d(TAG, "init: ");
    }


    private void setEvent() {
        getBinding().ivbtnOK.setOnClickListener(this);
        getBinding().ivDefaultAddCateBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivbtnOK:
                if(!getViewModel().isLoading()){
                    CategoryJson cate = getCateData();
                    if (menuId != null) {
                        saveToServer(cate);
                    }
                }
                break;
            case R.id.ivDefaultAddCateBack:
                showDiscardDialog();
                break;
        }
    }

    private void saveToServer(CategoryJson cate) {
        getViewModel().setLoading(true);
        CategoryService categoryService = NetContext.instance.create(CategoryService.class);
        Gson gson = new Gson();
        String json = gson.toJson(cate);
        RequestBody body = RequestBody.create(JSON, json);
        categoryService.addCategory(body).enqueue(new Callback<CategoryJson>() {
            @Override
            public void onResponse(Call<CategoryJson> call, Response<CategoryJson> response) {
                Log.e("AddCategory: Response ", response.toString());
                Log.e("AddCategory: Response ", Utils.bodyToString(call.request()));
                getViewModel().setLoading(false);
                onBackPressed();
            }

            @Override
            public void onFailure(Call<CategoryJson> call, Throwable t) {
                Log.e("Onfailed", call.request().toString());
                getViewModel().setLoading(false);
                Toast.makeText(AddCategoryActivity.this, "Falied To Commit", Toast.LENGTH_SHORT).show();
            }
        });
    }





    public void showDiscardDialog() {
        if (getBinding().etCateTitle.getText().toString().equals("")) {
            onBackPressed();
            return;
        }
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.cast_ic_notification_small_icon)
                .setTitle("Discard")
                .setMessage("You will lost input")
                .setPositiveButton("Sure", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onBackPressed();
                    }

                })
                .setNegativeButton("Cancel", null)
                .show();

    }

    public CategoryJson getCateData() {
        String cateName = getBinding().etCateTitle.getText().toString();
        String cateType = getBinding().etCateType.getText().toString();
        String cateID = UUID.randomUUID().toString();
        ;
        CategoryJson category = new CategoryJson(menuId, cateID, cateName, cateType, 1, new ArrayList<ItemJson>());
        Toast.makeText(this, category.getCateName() + " " + category.getCateId() + " " + category.getCateType() + " " + category.getMenuId(), Toast.LENGTH_SHORT).show();
        return category;
    }

}
