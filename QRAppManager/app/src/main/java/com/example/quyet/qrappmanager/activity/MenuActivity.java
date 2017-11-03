package com.example.quyet.qrappmanager.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
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


    private static final String TAG = "Menu activity";
    public String menuName;

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

        DefaultMenuViewPagerAdapter vpAdapter = new DefaultMenuViewPagerAdapter(getSupportFragmentManager());
        getBinding().vpCategory.setAdapter(vpAdapter);
        getBinding().tlDetailViewPagerTab.setupWithViewPager(getBinding().vpCategory);
        getBinding().tlDetailViewPagerTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        getBinding().vpCategory.setOffscreenPageLimit(0);
        getBinding().ivDefaultMenuBack.setOnClickListener(this);
        getBinding().fabAddCategory.setOnClickListener(this);
    }


    private void setEvent() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ivDefaultMenuBack:
                onBackPressed();
                break;
            case R.id.fabAddCategory :
                addCategory();
        }
    }

    private void addCategory() {
        Intent intent = new Intent(this, AddCategoryActivity.class);
        startActivity(intent);
    }



}
