package com.example.mho23.fbtwist.view;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mho23 on 2/5/18.
 */

public class MenuItem {
    private String name;
//    private int price;
//    private String img_url;
    private String description;

    public MenuItem(String n, int p) {
        this.name = n;
//        this.price = p;
//        this.img_url = url;
//        this.description = des;
    }


    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
//    public int getPrice() {
//        return price;
//    }
//
//    public void setPrice(int price) {
//        this.price = price;
//    }
//    public String getImg_url() {
//        return img_url;
//    }
//    public void setImg_url(String img_url) {
//        this.img_url = img_url;
//    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }


}

