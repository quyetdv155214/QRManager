package com.example.quyet.qrappmanager.networks.jsonmodel;

import com.example.quyet.qrappmanager.viewmodel.CategoryViewModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Quyet on 05/11/2017.
 */

public class CategoryJson {

    @SerializedName("id_data")
    private String idData;
    @SerializedName("menu_id")
    private String menuId;
    @SerializedName("cate_id")
    private String cateId;
    @SerializedName("cate_name")
    private String cateName;
    @SerializedName("cate_type")
    private String cateType;
    @SerializedName("cate_order")
    private int cateOrder;
    @SerializedName("items")
    private List<ItemJson> items;

    public CategoryJson(String menuId, String cateId, String cateName, String cateType, int cateOrder, List<ItemJson> items) {
        this.menuId = menuId;
        this.cateId = cateId;
        this.cateName = cateName;
        this.cateType = cateType;
        this.cateOrder = cateOrder;
        this.items = items;
    }

    public String getIdData() {
        return idData;
    }

    public void setIdData(String idData) {
        this.idData = idData;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getCateType() {
        return cateType;
    }

    public void setCateType(String cateType) {
        this.cateType = cateType;
    }

    public int getCateOrder() {
        return cateOrder;
    }

    public void setCateOrder(int cateOrder) {
        this.cateOrder = cateOrder;
    }

    public List<ItemJson> getItems() {
        return items;
    }

    public void setItems(List<ItemJson> items) {
        this.items = items;
    }

    public CategoryViewModel toViewModel() {
        return new CategoryViewModel(cateName, items, cateId, menuId, cateType);
    }

}
