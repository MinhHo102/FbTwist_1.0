package com.example.mho23.fbtwist.data;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by mho23 on 2/23/18.
 * Use to model items that users can add to checkout
 */

public class ItemOrdered {
//    public enum itemType {
//        TEA,
//        SMOOTHIE,
//        OTHER//ADD MORE AND MODIFY
//
//    }
    //type of item ordered, TEA, SMOOTHIE, OTHER, ADD MORE
    private String type;
    //name of item
    private String name;
    //Regular or Large
    private String size;
    //Milk(true) or Fruit(false), null if not Type is not activity_tea.
    private boolean TeaType;
    private boolean tapioca;
    //Keep track of what jellys or boba flavors user's choose
    private ArrayList<String> jelly = new ArrayList<>();
    private ArrayList<String> boba = new ArrayList<>();

    public ItemOrdered() {
    }

    public void setType(String type) {

        this.type = type;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setTeaType(boolean teaType) {
        TeaType = teaType;
    }

    public void setTapioca(boolean tapioca) {
        this.tapioca = tapioca;
    }

    public void setJelly(ArrayList<String> jelly) {
        this.jelly = jelly;
    }

    public void setBoba(ArrayList<String> boba) {
        this.boba = boba;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public String getSize() {
        return size;
    }

    public boolean isTeaType() {
        return TeaType;
    }

    public boolean isTapioca() {
        return tapioca;
    }

    public ArrayList<String> getJelly() {
        return jelly;
    }

    public ArrayList<String> getBoba() {
        return boba;
    }

    public ItemOrdered(String name, String type, String size, boolean teaType, boolean tapioca) {
        this.name = name;
        this.type = type;
        this.size = size;
        TeaType = teaType;
        this.tapioca = tapioca;
    }
}
