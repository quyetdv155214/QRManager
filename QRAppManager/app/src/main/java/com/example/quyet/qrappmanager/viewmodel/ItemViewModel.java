package com.example.quyet.qrappmanager.viewmodel;

import com.example.quyet.qrappmanager.model.Item;

import java.util.List;

/**
 * Created by Quyet on 02/11/2017.
 */

public class ItemViewModel extends BaseViewModel{
    private String itemId;
    private String itemName;
    private float itemPrice;
    private float itemDiscount;
    private String itemDes;
    private List<String> imageURL;
    private Item item;

    public ItemViewModel() {
    }

    public ItemViewModel(String itemId, String itemName, float itemPrice, float itemDiscount, String itemDes, List<String> imageURL) {
        this.itemId = itemId;
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

    public String getItemPrice() {
        return itemPrice +"";
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDiscount() {
        return itemDiscount + "";
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
