package com.example.quyet.qrappmanager.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.adapter.BaseRecyclerViewAdapter;
import com.example.quyet.qrappmanager.adapter.BaseSingleTypeRecyclerViewAdapter;
import com.example.quyet.qrappmanager.databinding.ActivityDetailCategoryBinding;
import com.example.quyet.qrappmanager.model.Item;
import com.example.quyet.qrappmanager.model.MenuCategory;
import com.example.quyet.qrappmanager.viewmodel.ActivityDetailCategoryViewModel;
import com.example.quyet.qrappmanager.viewmodel.ItemViewModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.category;

public class DetailCategoryActivity extends BaseActivity<ActivityDetailCategoryBinding, ActivityDetailCategoryViewModel> implements View.OnClickListener {
    private static final String TAG = "detaiCategory";
    BaseSingleTypeRecyclerViewAdapter<ItemViewModel> adapter;
    private List<ItemViewModel> itemList;
    private String cateName;
    private String cateType;
    private String menu;
    MenuCategory  category;
    private boolean editMode = false;
    private String categoryId;

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
        getData();
        setEvent();
        setView();
        setUpRecycleView(this);
    }

    private void setView() {
        getBinding().etCateTitle.setText(cateName);
        getBinding().etCateTitle.setFocusable(false);

    }

    private void setEvent() {
        getBinding().ivEdit.setOnClickListener(this);
        getBinding().ivbtnDel.setOnClickListener(this);
        getBinding().ivDefaultAddCateBack.setOnClickListener(this);
        getBinding().fabAddItem.setOnClickListener(this);
    }

    private void getData() {
        itemList = new ArrayList<>();
//        MenuCategory category = (MenuCategory)getIntent().getSerializableExtra("category");
        Gson gson = new Gson();
        category = gson.fromJson(getIntent().getStringExtra("category"), MenuCategory.class);
        Log.d(TAG, "getData: category " + category.toString());
        for(Item i : category.getItems()){
            itemList.add(new ItemViewModel(i));
        }
        cateName = category.getCatename();
        cateType = category.getCateType();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ivEdit :



                if(!editMode){
                    getBinding().etCateTitle.setEnabled(true);
                    getBinding().spCateMenu.setEnabled(true);
                    getBinding().spCateType.setEnabled(true);
                    getBinding().ivEdit.setImageResource(R.drawable.ic_save_white_24dp);
                    editMode = true;
                }else{
                    getBinding().etCateTitle.setEnabled(false);
                    getBinding().spCateMenu.setEnabled(false);
                    getBinding().spCateType.setEnabled(false);
                    getBinding().ivEdit.setImageResource(R.drawable.ic_edit_white_24dp);
                    editMode = false;
                    updateCategory();
                }

                break;
            case R.id.ivDefaultAddCateBack : onBackPressed();
                
                break;
            case R.id.ivbtnDel :
                new AlertDialog.Builder(this)
                        .setIcon(R.drawable.cast_ic_notification_small_icon)
                        .setTitle("delete")
                        .setMessage("delete this category")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                delete(category);
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
                break;
            case R.id.fabAddItem :
                addItem(categoryId);
                break;
        }
    }

    private void updateCategory() {
        Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();

    }

    private void delete(MenuCategory category) {
//        Toast.makeText(this, "delete " + category.toString(), Toast.LENGTH_SHORT).show();
        onBackPressed();

    }
    private void addItem(String categoryId) {
        Intent intent  = new Intent(this, AddItemActivity.class);
        intent.putExtra("categoryID", categoryId);
        startActivity(intent);
    }

    public class ItemListener implements BaseRecyclerViewAdapter.Presenter {
        public void onItemClick(Item item) {
            
        }
        public void onEditClick(Item item){
            Toast.makeText(DetailCategoryActivity.this, "click edit", Toast.LENGTH_SHORT).show();

        }

    }
    private void setUpRecycleView(Context context) {
        adapter = new BaseSingleTypeRecyclerViewAdapter<>(context, R.layout.default_item);
        LinearLayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        adapter.addAll(itemList);
        adapter.setPresenter(new ItemListener());


        getBinding().rvItem.setLayoutManager(layoutManager);
        getBinding().rvItem.setAdapter(adapter);
    }

}
