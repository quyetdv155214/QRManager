package com.example.quyet.qrappmanager.model;

import com.example.quyet.qrappmanager.ultil.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Quyet on 02/11/2017.
 */

public class Menu {
    private String menuName;
    private String menuId;
    private String managerID;
    private String describe;
    private List<MenuCategory> categories;


    public Menu(String menuName, String menuId, String resId, String describe, List<MenuCategory> categories) {
        this.menuName = menuName;
        this.menuId = menuId;
        this.managerID = managerID;
        this.describe = describe;
        this.categories = categories;
    }

    public Menu(String menuName, String menuId, String managerID, String describe) {
        this.menuName = menuName;
        this.menuId = menuId;
        this.managerID = managerID;
        this.describe = describe;
    }

    public Menu(String menuName, String managerID, String describe) {
        this.menuName = menuName;
        this.managerID = managerID;
        this.describe = describe;
    }


    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getManagerID() {
        return managerID;
    }

    public void setManagerID(String resId) {
        this.managerID = managerID;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public List<MenuCategory> getCategories() {
        return categories;
    }

    public void setCategories(ArrayList<MenuCategory> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return menuName;
    }
}
