package com.example.quyet.qrappmanager.networks.jsonmodel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Quyet on 05/11/2017.
 */

public class MenuResponseJson {


    @SerializedName("id_data")
    private String idData;
    @SerializedName("manager_id")
    private String managerId;
    @SerializedName("menu_id")
    private String menuId;
    @SerializedName("menu_name")
    private String menuName;
    @SerializedName("date_create")
    private String dateCreate;
    @SerializedName("describe")
    private String describe;
    @SerializedName("menu_pic")
    private String menuPic;
    @SerializedName("categories")
    private List<Categories> categories;
    @SerializedName("items")
    private List<Items> items;

    public String getIdData() {
        return idData;
    }

    public void setIdData(String idData) {
        this.idData = idData;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getMenuPic() {
        return menuPic;
    }

    public void setMenuPic(String menuPic) {
        this.menuPic = menuPic;
    }

    public List<Categories> getCategories() {
        return categories;
    }

    public void setCategories(List<Categories> categories) {
        this.categories = categories;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }

    public static class Categories {
    }

    public static class Items {
    }
}
