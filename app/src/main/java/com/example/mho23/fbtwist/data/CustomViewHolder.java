package com.example.mho23.fbtwist.data;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mho23.fbtwist.Interfaces.CallBackListener;
import com.example.mho23.fbtwist.R;

import java.util.ArrayList;

/**
 * Created by mho23 on 2/11/18.
 */

public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected TextView name;
    protected View container;
    private ArrayList<ListItem> data;
    private CallBackListener listener;

    public TextView getName() {
        return name;
    }

    public View getContainer() {
        return container;
    }

    public ArrayList<ListItem> getData() {
        return data;
    }

    public CallBackListener getListener() {
        return listener;
    }

    public CustomViewHolder(View itemView, ArrayList<ListItem> data, CallBackListener listener) {
        super(itemView);
        this.name = itemView.findViewById(R.id.cardViewName);
        this.container = itemView.findViewById(R.id.cardView1);
        this.data = data;
        this.listener = listener;
        this.container.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        ListItem listItem = data.get(this.getAdapterPosition());
        ArrayList<String> userOrders = listener.getUserOrders();
        listener.startDetailedActivity(listItem, userOrders);
    }
}
