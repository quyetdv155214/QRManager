package com.example.quyet.qrappmanager.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.databinding.library.baseAdapters.BR;
import com.example.quyet.qrappmanager.DBContext;
import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.adapter.BaseRecyclerViewAdapter;
import com.example.quyet.qrappmanager.databinding.ActivityMenuEditorBinding;
import com.example.quyet.qrappmanager.model.Menu;
import com.example.quyet.qrappmanager.networks.NetContext;
import com.example.quyet.qrappmanager.networks.jsonmodel.CategoryJson;
import com.example.quyet.qrappmanager.networks.jsonmodel.MenuJson;
import com.example.quyet.qrappmanager.networks.jsonmodel.MenuResponseJson;
import com.example.quyet.qrappmanager.networks.jsonmodel.MessageJson;
import com.example.quyet.qrappmanager.networks.services.CategoryService;
import com.example.quyet.qrappmanager.networks.services.MenuService;
import com.example.quyet.qrappmanager.ultil.Constant;
import com.example.quyet.qrappmanager.viewmodel.ActivityMenuEditorViewModel;
import com.example.quyet.qrappmanager.viewmodel.CategoryViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuEditorActivity extends BaseActivity<ActivityMenuEditorBinding, ActivityMenuEditorViewModel> implements View.OnClickListener {
    private String manager_id;

    @Override
    public int getLayoutId() {
        return R.layout.activity_menu_editor;
    }

    @Override
    public ActivityMenuEditorViewModel setViewModel() {
        return new ActivityMenuEditorViewModel(this);
    }


    @Override
    public int getVariableId() {
        return BR.viewModel;
    }

    @Override
    public void onCreateActivity() {
        manager_id = "thisisdemoid_3";
        init();
        requestMenu();

        getBinding().fbAddCategory.setOnClickListener(this);
    }

    private void init() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        getViewModel().getAdapter().set(new ArrayList<CategoryViewModel>());
        getViewModel().getAdapter().setPresenter(new ItemCategoryClick());
        getBinding().rvListCategory.setLayoutManager(layoutManager);
        getBinding().rvListCategory.setAdapter(getViewModel().getAdapter());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fbAddCategory:
                Intent intent = new Intent(MenuEditorActivity.this, AddCategoryActivity.class);
                intent.putExtra(Constant.PASS_MENUID, getViewModel().getMenuJson().getMenuId());
                startActivity(intent);
                break;
        }

    }

    public class ItemCategoryClick implements BaseRecyclerViewAdapter.Presenter {
        public void onClick(CategoryViewModel model) {
            Intent intent = new Intent(MenuEditorActivity.this, DetailCategoryActivity.class);
            Gson gson = new Gson();
            intent.putExtra(Constant.PASS_CATEGORYMODEL, gson.toJson(model));
            startActivity(intent);
        }

        public void onClickDelete(final CategoryViewModel model) {
            new android.support.v7.app.AlertDialog.Builder(MenuEditorActivity.this)
                    .setIcon(R.drawable.ic_delete_forever_accent_24dp)
                    .setTitle("Delete category " + model.getCatename())
                    .setMessage("Are you sure you want delete this Category?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            requestDeleteCategory(model.getCateID());
                        }

                    })
                    .setNegativeButton("No", null)
                    .show();
        }
    }

    public void requestMenu() {
        getViewModel().setLoading(true);
        MenuService menuService = NetContext.instance.create(MenuService.class);
        menuService.getMenu(manager_id).enqueue(new Callback<MenuJson>() {
            @Override
            public void onResponse(Call<MenuJson> call, Response<MenuJson> response) {
                getViewModel().setMenuJson(response.body());
                if (getViewModel().getMenuJson() != null && response.code() == 200) {
                    List<CategoryViewModel> modelList = new ArrayList<>();
                    for (CategoryJson categoryJson : getViewModel().getMenuJson().getCategories()) {
                        modelList.add(categoryJson.toViewModel());
                    }
                    getViewModel().getAdapter().set(modelList);

                    Log.e("Response", getViewModel().getMenuJson().toString());
                }
                getViewModel().setLoading(false);
            }

            @Override
            public void onFailure(Call<MenuJson> call, Throwable t) {
                getViewModel().setLoading(false);
                Log.e("OnFailed", call.request().toString() + t.toString());
            }
        });
    }

    public void requestDeleteCategory(String categoryId) {
        getViewModel().setLoading(true);
        CategoryService categoryService = NetContext.instance.create(CategoryService.class);
        categoryService.deleteCategoryWithId(categoryId).enqueue(new Callback<MessageJson>() {
            @Override
            public void onResponse(Call<MessageJson> call, Response<MessageJson> response) {
                getViewModel().setLoading(false);
                if (response.body() != null && response.isSuccessful()) {
                    MessageJson messageJson = response.body();
                    if (response.code() == 200) {
                        Toast.makeText(MenuEditorActivity.this, messageJson.getMessage(), Toast.LENGTH_SHORT).show();
                        requestMenu();
                    }
                }
                Log.e("Response", response.toString());
            }

            @Override
            public void onFailure(Call<MessageJson> call, Throwable t) {
                getViewModel().setLoading(false);
                Log.e("OnFailed", call.request().toString() + t.toString());
            }
        });

    }

    @Override
    protected void onResume() {
        requestMenu();
        super.onResume();
    }
}
