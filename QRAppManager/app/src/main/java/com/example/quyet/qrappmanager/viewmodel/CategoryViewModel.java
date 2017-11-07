package com.example.quyet.qrappmanager.viewmodel;

import com.example.quyet.qrappmanager.networks.jsonmodel.CategoryJson;
import com.example.quyet.qrappmanager.networks.jsonmodel.ItemJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Khuong Duy on 11/6/2017.
 */

public class CategoryViewModel extends BaseViewModel {
    private String catename;
    private List<ItemJson> items;
    private String cateID;
    private String menuId;
    private String cateType;

    public CategoryViewModel(String catename, List<ItemJson> items, String cateID, String menuId, String cateType) {
        this.catename = catename;
        this.items = items;
        this.cateID = cateID;
        this.menuId = menuId;
        this.cateType = cateType;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename;
    }

    public List<ItemJson> getItems() {
        return items;
    }

    public void setItems(List<ItemJson> items) {
        this.items = items;
    }

    public String getCateID() {
        return cateID;
    }

    public void setCateID(String cateID) {
        this.cateID = cateID;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getCateType() {
        return cateType;
    }

    public void setCateType(String cateType) {
        this.cateType = cateType;
    }

    public String getItemCount() {
        return items.size() + "";
    }

    public CategoryJson toCategoryJson() {
        return new CategoryJson(menuId, cateID, catename, cateType, 1, new ArrayList<ItemJson>());
    }
}
