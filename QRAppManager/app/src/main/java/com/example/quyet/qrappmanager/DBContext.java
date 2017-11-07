package com.example.quyet.qrappmanager;

import android.util.Log;

import com.example.quyet.qrappmanager.model.Item;
import com.example.quyet.qrappmanager.model.Menu;
import com.example.quyet.qrappmanager.model.MenuCategory;
import com.example.quyet.qrappmanager.viewmodel.ItemCategoryViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quyet on 05/11/2017.
 */

public class DBContext {
    public static final DBContext instance = new DBContext();
    private static final String TAG = "DBContext";
    private List<Menu> listMenu ;
    private List<MenuCategory> listCategories;
    private ArrayList<Item> items ;
    private List<ItemCategoryViewModel> categoryViewModelList ;
    private DBContext() {
        categoryViewModelList = new ArrayList<>();
        listCategories = new ArrayList<>();
        listMenu = new ArrayList<>();
        items = new ArrayList<>();
    }

    public List<ItemCategoryViewModel> getCategoryViewModelList() {
        return categoryViewModelList;
    }

    public void setCategoryViewModelList(List<ItemCategoryViewModel> categoryViewModelList) {
        this.categoryViewModelList = categoryViewModelList;
    }

    public List<Menu> getListMenu() {
        return listMenu;
    }

    public void setListMenu(List<Menu> listMenu) {
        this.listMenu = listMenu;
        Log.d(TAG, "setListMenu: list size " + this.listMenu.size());
    }

    public List<MenuCategory> getListCategories() {
        return listCategories;
    }

    public void setListCategories(List<MenuCategory> listCategories) {
        this.listCategories = listCategories;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
