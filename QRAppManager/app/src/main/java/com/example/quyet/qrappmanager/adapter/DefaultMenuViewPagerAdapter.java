package com.example.quyet.qrappmanager.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.quyet.qrappmanager.fragment.BaseFragment;
import com.example.quyet.qrappmanager.fragment.DefaultMenuFragment;
import com.example.quyet.qrappmanager.model.Item;
import com.example.quyet.qrappmanager.model.Menu;
import com.example.quyet.qrappmanager.model.MenuCategory;
import com.example.quyet.qrappmanager.viewmodel.ItemCategoryViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khuong Duy on 9/25/2017.
 */

public class DefaultMenuViewPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> list = new ArrayList<>();

    public DefaultMenuViewPagerAdapter(FragmentManager fm) {
        super(fm);
        addTab();

    }
    private  void addTab(){
        List<Menu> menu = getMenu();
        for (Menu m : menu){
            DefaultMenuFragment fragment1 = new DefaultMenuFragment();
            fragment1.setFragmentTitle(m.getMenuName());
            fragment1.setMenu(m);
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
    public List<Menu> getMenu(){
        List<Menu> listMenu = new ArrayList<>();
        List<MenuCategory> listCategories = new ArrayList<>();
        ArrayList<Item> items = new ArrayList<>();

        items.add(new Item("mon an 1", 16, 1,"so ngon"));
        items.add(new Item("mon an 2", 16, 1,"so ngon"));
        items.add(new Item("mon an 3", 16, 1,"so ngon"));
        items.add(new Item("mon an 4", 16, 1,"so ngon"));
        listCategories.add(new MenuCategory("cate 1", items, "1", "Menu0001", "001"));
        listCategories.add(new MenuCategory("cate 2", items, "2", "Menu0001", "001"));
        listCategories.add(new MenuCategory("cate 3", items, "3", "Menu0001", "001"));
        listCategories.add(new MenuCategory("cate 4", items, "4","Menu0001", "001"));
        listCategories.add(new MenuCategory("cate 5", items, "5","Menu0001", "001"));

        Menu menu = new Menu("Menu 21", "menu0001", "res11", "menu ngon", listCategories);
        listMenu.add(menu);
        return listMenu;
    }
}
