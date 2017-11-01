package com.example.quyet.qrappmanager.model;

import java.util.List;

/**
 * Created by Quyet on 31/10/2017.
 */

public class MenuCategory {
    private String Catename;
    private List<Item> items;


    public MenuCategory(String catename, List<Item> items) {
        Catename = catename;
        this.items = items;
    }
    public int getItemCount(){
        return items.size();
    }
    public String getCatename() {
        return Catename;
    }

    public void setCatename(String catename) {
        Catename = catename;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}