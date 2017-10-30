package com.example.quyet.qrappmanager.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.example.quyet.qrappmanager.BR;
import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.adapter.BaseRecyclerViewAdapter;
import com.example.quyet.qrappmanager.adapter.BaseSingleTypeRecyclerViewAdapter;
import com.example.quyet.qrappmanager.adapter.CategoryRecycleAdapter;
import com.example.quyet.qrappmanager.databinding.ActivityMenuBinding;
import com.example.quyet.qrappmanager.viewmodel.ActivityMenuViewModel;
import com.example.quyet.qrappmanager.model.*;
import com.example.quyet.qrappmanager.viewmodel.ItemCategoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends BaseActivity<ActivityMenuBinding, ActivityMenuViewModel>{


    @Override
    public int getLayoutId() {
        return R.layout.activity_menu;
    }

    @Override
    public ActivityMenuViewModel setViewModel() {
        return new ActivityMenuViewModel();
    }

    @Override
    public int getVariableId() {
        return BR.viewModel;
    }

    @Override
    public void onCreateActivity() {
        init();

    }
    private void init() {
        setEvent();
        setRecycleView();
    }

    private void setRecycleView() {
        List<ItemCategoryViewModel> menuActivityList = new ArrayList<>();
        menuActivityList.add(new ItemCategoryViewModel(new MenuCategory("cate 1", new ArrayList<Item>())));
        menuActivityList.add(new ItemCategoryViewModel(new MenuCategory("cate 2", new ArrayList<Item>())));
        menuActivityList.add(new ItemCategoryViewModel(new MenuCategory("cate 3", new ArrayList<Item>())));
        menuActivityList.add(new ItemCategoryViewModel(new MenuCategory("cate 4", new ArrayList<Item>())));
        menuActivityList.add(new ItemCategoryViewModel(new MenuCategory("cate 5", new ArrayList<Item>())));


        BaseSingleTypeRecyclerViewAdapter<ItemCategoryViewModel> myRvAdapter = new BaseSingleTypeRecyclerViewAdapter<>(this, R.layout.category_item);
        myRvAdapter.addAll(menuActivityList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        myRvAdapter.setPresenter(new CategoryListener());
        getBinding().rvCategory.setLayoutManager(layoutManager);
        getBinding().rvCategory.setAdapter(myRvAdapter);
    }

    private void setEvent() {

    }

    public class CategoryListener implements BaseRecyclerViewAdapter.Presenter {
        public void onItemClick(MenuCategory category){
            Toast.makeText(MenuActivity.this, category.getCatename(), Toast.LENGTH_SHORT).show();

        }
    }


}
