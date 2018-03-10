package com.example.mho23.fbtwist.data;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.mho23.fbtwist.R;

import java.util.ArrayList;

/**
 * Created by mho23 on 2/25/18.
 */

public class CheckoutViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected TextView name;
    protected View container;
    protected TextView description;
    protected TextView index;
    private ArrayList<ItemOrdered> data;

    public TextView getName() {
        return name;
    }

    public TextView getDescription() {
        return description;
    }

    public View getContainer() {
        return container;
    }

    public ArrayList<ItemOrdered> getData() {
        return data;
    }

    public TextView getIndex() {
        return index;
    }

    public void setIndex(TextView index) {
        this.index = index;
    }

    public CheckoutViewHolder(View itemView, ArrayList<ItemOrdered> data) {
        super(itemView);
        this.name = itemView.findViewById(R.id.checkoutCardViewName);
        this.container = itemView.findViewById(R.id.checkoutCardView);
        this.description = itemView.findViewById(R.id.checkoutCardViewDescription);
        this.data = data;
        this.index = itemView.findViewById(R.id.CheckoutIndex);
    }
    @Override
    public void onClick(View v) {

    }
}
