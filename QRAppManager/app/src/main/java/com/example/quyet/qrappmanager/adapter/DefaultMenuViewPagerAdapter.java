package com.example.quyet.qrappmanager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.quyet.qrappmanager.fragment.BaseFragment;
import com.example.quyet.qrappmanager.fragment.DefaultMenuFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khuong Duy on 9/25/2017.
 */

public class DefaultMenuViewPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> list = new ArrayList<>();

    public DefaultMenuViewPagerAdapter(FragmentManager fm) {
        super(fm);
        DefaultMenuFragment fragment1 = new DefaultMenuFragment();
        fragment1.setFragmentTitle("Menu 1");
//        DefaultMenuFragment fragment2 = new DefaultMenuFragment();
//        fragment2.setFragmentTitle("Menu 2");
        list.add(fragment1);
//        list.add(fragment2);
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
}
