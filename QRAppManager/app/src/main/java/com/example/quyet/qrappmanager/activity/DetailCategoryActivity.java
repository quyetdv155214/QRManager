package com.example.quyet.qrappmanager.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.adapter.BaseRecyclerViewAdapter;
import com.example.quyet.qrappmanager.adapter.BaseSingleTypeRecyclerViewAdapter;
import com.example.quyet.qrappmanager.databinding.ActivityDetailCategoryBinding;
import com.example.quyet.qrappmanager.model.Item;
import com.example.quyet.qrappmanager.networks.NetContext;
import com.example.quyet.qrappmanager.networks.jsonmodel.CategoryJson;
import com.example.quyet.qrappmanager.networks.jsonmodel.ItemJson;
import com.example.quyet.qrappmanager.networks.services.CategoryService;
import com.example.quyet.qrappmanager.networks.services.GetItemService;
import com.example.quyet.qrappmanager.ultil.Constant;
import com.example.quyet.qrappmanager.ultil.Utils;
import com.example.quyet.qrappmanager.viewmodel.ActivityDetailCategoryViewModel;
import com.example.quyet.qrappmanager.viewmodel.CategoryViewModel;
import com.example.quyet.qrappmanager.viewmodel.ItemViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailCategoryActivity extends BaseActivity<ActivityDetailCategoryBinding, ActivityDetailCategoryViewModel> implements View.OnClickListener {
    private static final String TAG = "detaiCategory";
    public static final MediaType JSON = MediaType.parse("application/json");
    BaseSingleTypeRecyclerViewAdapter<ItemViewModel> adapter;
    private boolean isEditing = false;


    @Override
    public int getLayoutId() {
        return R.layout.activity_detail_category;
    }

    @Override
    public ActivityDetailCategoryViewModel setViewModel() {
        return new ActivityDetailCategoryViewModel();
    }

    @Override
    public int getVariableId() {
        return com.example.quyet.qrappmanager.BR.viewModel;
    }

    @Override
    public void onCreateActivity() {
        init();
    }

    private void init() {
        getSupportActionBar().hide();
        setStatusBarTranslucent(true);
        setUpRecycleView(this);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getViewModel().setCategoryViewModel(getGson().fromJson(getIntent().getStringExtra(Constant.PASS_CATEGORYMODEL), CategoryViewModel.class));
        if (getViewModel().getCategoryViewModel() != null) {
            requestItemWithCategoryId(getViewModel().getCategoryViewModel().getCateID());
        }
        getBinding().ivDefaultAddCateBack.setOnClickListener(this);
        getBinding().ivCategoryEdit.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivDefaultAddCateBack:
                onBackPressed();
                break;
            case R.id.ivCategoryEdit:
                if (isEditing) {
                    setEditing(false);
                    requestEditCategory();
                } else {
                    setEditing(true);
                }
                break;
            case R.id.fbAddItem:
                Intent intent = new Intent(DetailCategoryActivity.this, DetailItemActivity.class);
                intent.putExtra(Constant.ADD_ITEM,true);
                startActivity(intent);
                break;

        }
    }

    private void setEditing(boolean editing) {
        if (!editing) {
            isEditing = false;
            getBinding().etCateTitle.setEnabled(false);
            getBinding().etCateTypeDetail.setEnabled(false);
            getBinding().ivCategoryEdit.setImageDrawable(getResources().getDrawable(R.drawable.ic_edit_white_24dp));
            hideKeyboard(getBinding().etCateTitle);
        } else {
            isEditing = true;
            getBinding().etCateTitle.setEnabled(true);
            getBinding().etCateTypeDetail.setEnabled(true);
            getBinding().ivCategoryEdit.setImageDrawable(getResources().getDrawable(R.drawable.ic_check_primary_24dp));
            showKeyboard(getBinding().etCateTitle);
        }
    }

    public class ItemListener implements BaseRecyclerViewAdapter.Presenter {
        public void onItemClick(ItemViewModel item) {
            Intent intent = new Intent(getApplicationContext(), DetailItemActivity.class);
            Gson gson = new Gson();
            String itemJson = gson.toJson(item);
            intent.putExtra(Constant.PASS_ITEMODEL, itemJson);
            startActivity(intent);
        }

        public void onEditClick(Item item) {
            Toast.makeText(DetailCategoryActivity.this, "click edit", Toast.LENGTH_SHORT).show();

        }

    }

    private void requestItemWithCategoryId(String categoryId) {
        getViewModel().setLoading(true);
        GetItemService getItemService = NetContext.instance.create(GetItemService.class);
        getItemService.getMenuWithId(categoryId).enqueue(new Callback<List<ItemJson>>() {
            @Override
            public void onResponse(Call<List<ItemJson>> call, Response<List<ItemJson>> response) {
                List<ItemViewModel> itemList = new ArrayList<>();
                for (ItemJson itemResponseJson : response.body()) {
                    itemList.add(itemResponseJson.toItemViewModel());
                }
                adapter.set(itemList);
                getViewModel().setLoading(false);
                Log.e("Response", response.toString());
            }

            @Override
            public void onFailure(Call<List<ItemJson>> call, Throwable t) {
                Log.e("Failed", call.toString());
                getViewModel().setLoading(false);
            }
        });
    }

    private void requestEditCategory() {
        getViewModel().setLoading(true);
        CategoryService categoryService = NetContext.instance.create(CategoryService.class);
        getViewModel().getCategoryViewModel().setCatename(getBinding().etCateTitle.getText().toString());
        getViewModel().getCategoryViewModel().setCateType(getBinding().etCateTypeDetail.getText().toString());
        final RequestBody body = RequestBody.create(JSON, getGson().toJson(getViewModel().getCategoryViewModel().toCategoryJson()));
        categoryService.editCategoryWithId(getViewModel().getCategoryViewModel().getCateID(), body).enqueue(new Callback<CategoryJson>() {
            @Override
            public void onResponse(Call<CategoryJson> call, Response<CategoryJson> response) {
                Log.e(TAG, response.toString());
                Log.e(TAG, Utils.bodyToString(call.request()));
                if (response.body() != null && response.isSuccessful()) {
                    CategoryJson categoryJson = response.body();
                    if (categoryJson.getCateName().equals(getViewModel().getCategoryViewModel().getCatename())) {
                        Toast.makeText(DetailCategoryActivity.this, "Changed", Toast.LENGTH_SHORT).show();
                    }
                }
                getViewModel().setLoading(false);
            }

            @Override
            public void onFailure(Call<CategoryJson> call, Throwable t) {
                Log.e(TAG, "Failed" + call.toString());
                getViewModel().setLoading(false);
            }
        });
    }

    private void setUpRecycleView(Context context) {
        adapter = new BaseSingleTypeRecyclerViewAdapter<>(context, R.layout.item_item);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        adapter.addAll(new ArrayList<ItemViewModel>());
        adapter.setPresenter(new ItemListener());


        getBinding().rvItem.setLayoutManager(layoutManager);
        getBinding().rvItem.setAdapter(adapter);
    }


}
