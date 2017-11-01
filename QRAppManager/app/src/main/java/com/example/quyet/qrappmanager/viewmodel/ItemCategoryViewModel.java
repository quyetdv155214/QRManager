package com.example.quyet.qrappmanager.viewmodel;

import android.databinding.Bindable;

import com.example.quyet.qrappmanager.model.Item;
import com.example.quyet.qrappmanager.model.MenuCategory;

import java.util.List;

/**
 * Created by Quyet on 31/10/2017.
 */

public class ItemCategoryViewModel extends BaseViewModel {
    private String catename;
    private List<Item> items;
    private MenuCategory category;


    public ItemCategoryViewModel(MenuCategory category) {
        this.catename = category.getCatename();
        this.items = category.getItems();
        this.category = category;
    }

    public String getItemCount() {
        return items.size() + "";
    }

    public MenuCategory getCategory() {
        return category;
    }

    public void setCategory(MenuCategory category) {
        this.category = category;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
