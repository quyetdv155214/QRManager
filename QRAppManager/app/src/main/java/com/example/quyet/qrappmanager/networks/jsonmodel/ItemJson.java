package com.example.quyet.qrappmanager.networks.jsonmodel;

import com.example.quyet.qrappmanager.model.Item;
import com.example.quyet.qrappmanager.viewmodel.ItemViewModel;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Quyet on 05/11/2017.
 */

public class ItemJson {

    @SerializedName("_id")
    private Id Id;
    @SerializedName("menu_id")
    private String menuId;
    @SerializedName("cate_id")
    private String cateId;
    @SerializedName("item_id")
    private String itemId;
    @SerializedName("item_name")
    private String itemName;
    @SerializedName("item_price")
    private double itemPrice;
    @SerializedName("item_old_price")
    private double itemOldPrice;
    @SerializedName("item_discount")
    private int itemDiscount;
    @SerializedName("item_desc")
    private String itemDesc;
    @SerializedName("item_view_count")
    private int itemViewCount;
    @SerializedName("item_info")
    private String itemInfo;
    @SerializedName("item_images_url")
    private List<String> itemImagesUrl;

    public Id getId() {
        return Id;
    }

    public void setId(Id Id) {
        this.Id = Id;
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

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public double getItemOldPrice() {
        return itemOldPrice;
    }

    public void setItemOldPrice(double itemOldPrice) {
        this.itemOldPrice = itemOldPrice;
    }

    public int getItemDiscount() {
        return itemDiscount;
    }

    public void setItemDiscount(int itemDiscount) {
        this.itemDiscount = itemDiscount;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public int getItemViewCount() {
        return itemViewCount;
    }

    public void setItemViewCount(int itemViewCount) {
        this.itemViewCount = itemViewCount;
    }

    public String getItemInfo() {
        return itemInfo;
    }

    public void setItemInfo(String itemInfo) {
        this.itemInfo = itemInfo;
    }

    public List<String> getItemImagesUrl() {
        return itemImagesUrl;
    }

    public void setItemImagesUrl(List<String> itemImagesUrl) {
        this.itemImagesUrl = itemImagesUrl;
    }

    public static class Id {
        @SerializedName("$oid")
        private String $oid;

        public String get$oid() {
            return $oid;
        }

        public void set$oid(String $oid) {
            this.$oid = $oid;
        }
    }

    @Override
    public String toString() {
        return "ItemJson{" +
                "Id=" + Id +
                ", menuId='" + menuId + '\'' +
                ", cateId='" + cateId + '\'' +
                ", itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", itemOldPrice=" + itemOldPrice +
                ", itemDiscount=" + itemDiscount +
                ", itemDesc='" + itemDesc + '\'' +
                ", itemViewCount=" + itemViewCount +
                ", itemInfo='" + itemInfo + '\'' +
                ", itemImagesUrl=" + itemImagesUrl +
                '}';
    }

    public ItemViewModel toItemViewModel() {
        return new ItemViewModel(itemId, itemName, Float.valueOf(String.valueOf(itemPrice)), itemDiscount, itemDesc, itemImagesUrl);
    }
}
