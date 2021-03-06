package com.example.quyet.qrappmanager.model;

import java.util.List;

/**
 * Created by Quyet on 31/10/2017.
 */

public class MenuCategory {
    private String catename;
    private List<Item> items;
    private String cateID;
    private String menuId;
    private String cateType;


    public MenuCategory(String catename, String cateID, String menuId, String cateType) {
        this.catename = catename;
        this.cateID = cateID;
        this.menuId = menuId;
        this.cateType = cateType;
    }

    public MenuCategory(String catename, List<Item> items, String cateID, String menuId, String cateType) {
        this.catename = catename;
        this.items = items;
        this.cateID = cateID;
        this.menuId = menuId;
        this.cateType = cateType;
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

    public String getCateID() {
        return cateID;
    }

    public void setCateID(String cateID) {
        this.cateID = cateID;
    }

    public int getItemCount(){
        return items.size();
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
        return "MenuCategory{" +
                "catename='" + catename + '\'' +
                ", items=" + items +
                ", cateID='" + cateID + '\'' +
                ", menuId='" + menuId + '\'' +
                ", cateType='" + cateType + '\'' +
                '}';
    }
}
