package com.example.quyet.qrappmanager.adapter;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.example.quyet.qrappmanager.DBContext;
import com.example.quyet.qrappmanager.activity.LoginActivity;
import com.example.quyet.qrappmanager.fragment.BaseFragment;
import com.example.quyet.qrappmanager.fragment.DefaultMenuFragment;
import com.example.quyet.qrappmanager.model.Item;
import com.example.quyet.qrappmanager.model.Menu;
import com.example.quyet.qrappmanager.model.MenuCategory;
import com.example.quyet.qrappmanager.networks.NetContext;
import com.example.quyet.qrappmanager.networks.jsonmodel.MenuResponseJson;
import com.example.quyet.qrappmanager.networks.services.MenuService;
import com.example.quyet.qrappmanager.viewmodel.ItemCategoryViewModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Khuong Duy on 9/25/2017.
 */

public class DefaultMenuViewPagerAdapter extends FragmentPagerAdapter {
    private static final String TAG = "menu_view_pager";
    private List<BaseFragment> list = new ArrayList<>();


    public DefaultMenuViewPagerAdapter(FragmentManager fm) {
        super(fm);
        setView();
        addTab();

    }

    private void setView() {

    }

    private void addTab() {
//        getMenu();
        Log.d(TAG, "addTab: " + DBContext.instance.getListMenu().size());
        for (Menu m : DBContext.instance.getListMenu()) {
            DefaultMenuFragment fragment1 = new DefaultMenuFragment();
            fragment1.setFragmentTitle(m.getMenuName());
            Log.d(TAG, "addTab: " + m.getMenuId());
            fragment1.setMenu(m);
//            Log.d(TAG, "addTab: " +m.toString());
            list.add(fragment1);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return list.get(position).getFragmentTitle();
    }

    @Override
    public int getCount() {
        return list.size();
    }




    public void setList(List<BaseFragment> list) {
        this.list = list;
    }


}
