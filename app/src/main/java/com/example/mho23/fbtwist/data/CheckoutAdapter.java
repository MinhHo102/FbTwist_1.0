package com.example.mho23.fbtwist.data;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mho23.fbtwist.R;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by mho23 on 2/25/18.
 */

public class CheckoutAdapter extends RecyclerView.Adapter<CheckoutViewHolder> {
    private Context c;
    private ArrayList<ItemOrdered> data;
    //listener?

    public CheckoutAdapter(Context c, ArrayList<ItemOrdered> data) {
        this.c = c;
        this.data = data;
    }

    @Override
    public CheckoutViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.checkoutcard, parent, false);
        return new CheckoutViewHolder(v, data);
    }

    @Override
    public void onBindViewHolder(CheckoutViewHolder holder, int position) {
        String cardName = getCardName(position);
        String cardDescription = getCardDescription(position);
        holder.getName().setText(cardName);
        holder.getDescription().setText(cardDescription);
        String pos = String.valueOf(position+1);
        StringBuilder s = new StringBuilder("Item #");
        s.append(pos);
        holder.getIndex().setText(s);
    }

    private String getCardDescription(int position) {
        ItemOrdered currentItem = data.get(position);
        //concatenate description that includes tapioca?, jellies and bobas
        StringBuilder tapioca = new StringBuilder(), jelly = new StringBuilder(), boba = new StringBuilder();
        ArrayList<String> jellyArray = currentItem.getJelly();
        ArrayList<String> bobaArray = currentItem.getBoba();

        if (currentItem.isTapioca()) tapioca.append("Tapioca: YES");
        else tapioca.append("Tapioca: NO");

        if (!jellyArray.isEmpty()) {
            for (int i = 0; i < jellyArray.size(); i++) {
                jelly.append(jellyArray.get(i));
                jelly.append(", ");
            }
        }
        if (!bobaArray.isEmpty()) {
            for (int i = 0; i < bobaArray.size(); i++) {
                boba.append(bobaArray.get(i));
                boba.append(", ");
            }
        }
        return tapioca + "\nJelly: " + jelly + "\nBoba: " + boba;
    }

    private String getCardName(int position) {
        ItemOrdered currentItem = data.get(position);
        String itemType = currentItem.getType();
        if (itemType.toLowerCase().contains("tea")) itemType = "Tea";
        else if (itemType.toLowerCase().contains("smoothie")) itemType = "Smoothie";
        else if (itemType.toLowerCase().contains("coffee")) itemType = "Coffee";
        else itemType = " ";
        String size = currentItem.getSize();
        String fruitOrMilk = currentItem.isTeaType() ? "Milk" : "Fruit";
        return size + " " + fruitOrMilk + " " + currentItem.getName() + " " + itemType;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
