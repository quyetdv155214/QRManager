package com.example.quyet.qrappmanager.viewmodel;

import android.databinding.Bindable;

import com.example.quyet.qrappmanager.model.Item;
import com.example.quyet.qrappmanager.model.MenuCategory;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Quyet on 31/10/2017.
 */
public class ItemCategoryViewModel extends BaseViewModel  {
    private String catename;
    private List<Item> items;
    private String cateID;
    private String menuId;
    private String cateType;
    MenuCategory category;

    public ItemCategoryViewModel(MenuCategory category) {
        this.catename = category.getCatename();
        this.items = category.getItems();
        this.menuId = category.getMenuId();
        this.cateType = category.getCateType();
        this.category = category;
        this.cateID = category.getCateID();
    }


    public String getCateID() {
        return cateID;
    }

    public void setCateID(String cateID) {
        this.cateID = cateID;
    }

    public String getItemCount() {
        return items.size() + " item";
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

    @Override
    public String toString() {
        return "ItemCategoryViewModel{" +
                "catename='" + catename + '\'' +
                ", items=" + items +
                ", cateID='" + cateID + '\'' +
                ", menuId='" + menuId + '\'' +
                ", cateType='" + cateType + '\'' +
                ", category=" + category +
                '}';
    }
}
