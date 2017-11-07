package com.example.quyet.qrappmanager.networks.jsonmodel;

import com.example.quyet.qrappmanager.ultil.Utils;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quyet on 05/11/2017.
 */

public class MenuJson {
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
    private List<CategoryJson> categories;

    public MenuJson(String managerId, String menuName, String describe, String menuPic) {
        this.managerId = managerId;
        this.menuId = Utils.instance.getUUID();
        this.menuName = menuName;
        this.dateCreate = "";
        this.describe = describe;
        this.menuPic = menuPic;
        this.categories = new ArrayList<>();
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

    public List<CategoryJson> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryJson> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "MenuJson{" +
                "managerId='" + managerId + '\'' +
                ", menuId='" + menuId + '\'' +
                ", menuName='" + menuName + '\'' +
                ", dateCreate='" + dateCreate + '\'' +
                ", describe='" + describe + '\'' +
                ", menuPic='" + menuPic + '\'' +
                ", categories=" + categories +
                '}';
    }
}
