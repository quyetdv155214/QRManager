package com.example.quyet.qrappmanager.model;

import java.util.List;
import java.util.UUID;

/**
 * Created by Quyet on 31/10/2017.
 */

public class Item {
    private String itemId;
    private String itemName;
    private float itemPrice;
    private float itemDiscount;
    private String itemDes;
    private List<String> imageURL;



    public Item( String itemName, float itemPrice, float itemDiscount, String itemDes) {
        this.itemId = UUID.randomUUID().toString();;;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDiscount = itemDiscount;
        this.itemDes = itemDes;
    }

    public Item(String itemName, float itemPrice, float itemDiscount, String itemDes, List<String> imageURL) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemDiscount = itemDiscount;
        this.itemDes = itemDes;
        this.imageURL = imageURL;
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

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public float getItemDiscount() {
        return itemDiscount;
    }

    public void setItemDiscount(float itemDiscount) {
        this.itemDiscount = itemDiscount;
    }

    public String getItemDes() {
        return itemDes;
    }

    public void setItemDes(String itemDes) {
        this.itemDes = itemDes;
    }

    public List<String> getImageURL() {
        return imageURL;
    }

    public void setImageURL(List<String> imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", itemDiscount=" + itemDiscount +
                ", itemDes='" + itemDes + '\'' +
                ", imageURL=" + imageURL +
                '}';
    }
}
