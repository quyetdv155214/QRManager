package com.example.quyet.qrappmanager.activity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.quyet.qrappmanager.BR;
import com.example.quyet.qrappmanager.DBContext;
import com.example.quyet.qrappmanager.R;
import com.example.quyet.qrappmanager.adapter.BaseRecyclerViewAdapter;
import com.example.quyet.qrappmanager.adapter.BaseSingleTypeRecyclerViewAdapter;
import com.example.quyet.qrappmanager.adapter.DefaultMenuViewPagerAdapter;
import com.example.quyet.qrappmanager.databinding.ActivityMenuBinding;
import com.example.quyet.qrappmanager.networks.NetContext;
import com.example.quyet.qrappmanager.networks.jsonmodel.MenuResponseJson;
import com.example.quyet.qrappmanager.networks.services.MenuService;
import com.example.quyet.qrappmanager.viewmodel.ActivityMenuViewModel;
import com.example.quyet.qrappmanager.model.*;
import com.example.quyet.qrappmanager.viewmodel.ItemCategoryViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MenuActivity extends BaseActivity<ActivityMenuBinding, ActivityMenuViewModel> implements View.OnClickListener {


    private static final String TAG = "Menu activity";
    public String menuName;
    private ProgressDialog pDialog;
    DefaultMenuViewPagerAdapter vpAdapter;

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
//        getMenu();

        DefaultMenuViewPagerAdapter vpAdapter = new DefaultMenuViewPagerAdapter(getSupportFragmentManager());
        getBinding().vpCategory.setAdapter(vpAdapter);
        getBinding().tlDetailViewPagerTab.setupWithViewPager(getBinding().vpCategory);
        getBinding().tlDetailViewPagerTab.setTabMode(TabLayout.MODE_SCROLLABLE);
        getBinding().vpCategory.setOffscreenPageLimit(0);
        getBinding().ivDefaultMenuBack.setOnClickListener(this);
        getBinding().fabAddCategory.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        DefaultMenuViewPagerAdapter vpAdapter = new DefaultMenuViewPagerAdapter(getSupportFragmentManager());
        getBinding().vpCategory.setAdapter(vpAdapter);
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
//    public void getMenu() {
//        MenuService menuService = NetContext.instance.create(MenuService.class);
//        menuService.getMenu(LoginActivity.managerId).enqueue(new Callback<List<MenuResponseJson>>() {
//            @Override
//            public void onResponse(Call<List<MenuResponseJson>> call, Response<List<MenuResponseJson>> response) {
//                List<MenuResponseJson> responseJsons = response.body();
//                Log.d(TAG, "onResponse: " + response.body());
//                if (responseJsons != null) {
//                    List<Menu> listMenu = new ArrayList<Menu>();
//                    if (response.code() == 200) {
//                        for (MenuResponseJson mj :
//                                responseJsons) {
//                            Menu m = new Menu(mj.getMenuName(),mj.getMenuId(), mj.getManagerId(), mj.getDescribe());
//                            Log.d(TAG, "onResponse: menu item " + m.toString());
//                            listMenu.add(m);
//                        }
//                        DBContext.instance.setListMenu(listMenu);
//                        vpAdapter.notifyDataSetChanged();
//                        Log.d(TAG, "onResponse: " + DBContext.instance.getListMenu().size());
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<MenuResponseJson>> call, Throwable t) {
//                Log.d(TAG, "onFailure: ");
//                pDialog.dismiss();
//            }
//        });
//
//
//    }




}
