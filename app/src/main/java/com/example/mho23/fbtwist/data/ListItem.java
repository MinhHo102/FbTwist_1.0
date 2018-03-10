package com.example.mho23.fbtwist.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mho23 on 2/6/18.
 */

public class ListItem{
    private String itemName;
    private String description;

//Can have multiple constructors with varying parameters for the many different lists we'll make

    public ListItem() {
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public int getId() {
        return id;
    }
    //    private String itemPrice;
//    private String itemImageUrl;
//    private String Description;

//    public ListItem(String itemName) {
//        this.itemName = itemName;
////        this.itemPrice = itemPrice;
////        this.itemImageUrl = itemImageUrl;
////        Description = description;
//    }

    public String getItemName() {
        return itemName;
    }

//    public String getItemPrice() {
//        return itemPrice;
//    }

//    public String getItemImageUrl() {
//        return itemImageUrl;
//    }
//
    public String getDescription() {
        return description;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

//    public void setItemPrice(String itemPrice) {
//        this.itemPrice = itemPrice;
//    }

//    public void setItemImageUrl(String itemImageUrl) {
//        this.itemImageUrl = itemImageUrl;
//    }
//
    public void setDescription(String description) {
        this.description = description;
    }

}
