package com.example.quyet.qrappmanager.activity;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Toast;

import com.example.quyet.qrappmanager.BR;
import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.adapter.BaseRecyclerViewAdapter;
import com.example.quyet.qrappmanager.adapter.BaseSingleTypeRecyclerViewAdapter;
import com.example.quyet.qrappmanager.adapter.DefaultMenuViewPagerAdapter;
import com.example.quyet.qrappmanager.databinding.ActivityMenuBinding;
import com.example.quyet.qrappmanager.viewmodel.ActivityMenuViewModel;
import com.example.quyet.qrappmanager.model.*;
import com.example.quyet.qrappmanager.viewmodel.ItemCategoryViewModel;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends BaseActivity<ActivityMenuBinding, ActivityMenuViewModel> implements View.OnClickListener {


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
        getSupportActionBar().hide();
        setStatusBarTranslucent(true);
        setEvent();
//        setRecycleView();
        DefaultMenuViewPagerAdapter vpAdapter = new DefaultMenuViewPagerAdapter(getSupportFragmentManager());
        getBinding().vpCategory.setAdapter(vpAdapter);
        getBinding().tlDetailViewPagerTab.setupWithViewPager(getBinding().vpCategory);
        getBinding().tlDetailViewPagerTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        getBinding().vpCategory.setOffscreenPageLimit(0);
        getBinding().ivDefaultMenuBack.setOnClickListener(this);
    }


//    private void setRecycleView() {
//        List<ItemCategoryViewModel> menuActivityList = new ArrayList<>();
//        menuActivityList.add(new ItemCategoryViewModel(new MenuCategory("cate 1", new ArrayList<Item>())));
//        menuActivityList.add(new ItemCategoryViewModel(new MenuCategory("cate 2", new ArrayList<Item>())));
//        menuActivityList.add(new ItemCategoryViewModel(new MenuCategory("cate 3", new ArrayList<Item>())));
//        menuActivityList.add(new ItemCategoryViewModel(new MenuCategory("cate 4", new ArrayList<Item>())));
//        menuActivityList.add(new ItemCategoryViewModel(new MenuCategory("cate 5", new ArrayList<Item>())));
//
//
//        BaseSingleTypeRecyclerViewAdapter<ItemCategoryViewModel> myRvAdapter = new BaseSingleTypeRecyclerViewAdapter<>(this, R.layout.category_item);
//        myRvAdapter.addAll(menuActivityList);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
//        myRvAdapter.setPresenter(new CategoryListener());
//        getBinding().rvCategory.setLayoutManager(layoutManager);
//        getBinding().rvCategory.setAdapter(myRvAdapter);
//    }

    private void setEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivDefaultMenuBack:
                onBackPressed();
                break;
        }
    }


}
