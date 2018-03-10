package com.example.mho23.fbtwist.data;

import android.support.v7.widget.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mho23.fbtwist.Interfaces.CallBackListener;
import com.example.mho23.fbtwist.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mho23 on 2/11/18
 */

public class MyAdapter extends RecyclerView.Adapter<CustomViewHolder>{

    private Context c;
    private ArrayList<ListItem> data;
    private CallBackListener listener;

    public MyAdapter(Context c, ArrayList<ListItem> data, CallBackListener listener) {
        this.c = c;
        this.data = data;
        this.listener = listener;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(c).inflate(R.layout.cardview, parent,false);
        return new CustomViewHolder(v, data, listener);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        ListItem currentItem = data.get(position);
        holder.getName().setText(currentItem.getItemName());
    }


    @Override
    public int getItemCount() {
        return data.size();
    }
    /*
    * Function is responsible for swapping empty list, initially used to instantiate the adapter, with the
    * new list passed back from creating (and running) the asynctask class, that passes in a URL upon
    * instantiation. notifyDataSetChanged also updates the recyclerview to accomodate new set of data.
    * */
    public void swap(ArrayList<ListItem> list) {
        if (list == null || list.size() == 0)
            return;
        if (data != null && data.size() > 0)
            data.clear();
        data.addAll(list);
        notifyDataSetChanged();
    }

//    public class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        private TextView name;
//        private ViewGroup container;
//
//        public CustomViewHolder(View itemView) {
//            super(itemView);
//            this.name = itemView.findViewById(R.id.cardViewName);
//            this.container = itemView.findViewById(R.id.cardView1);
//
//            this.container.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View v) {
//            ListItem listItem = data.get(this.getAdapterPosition());
//            listener.startDetailedActivity(listItem);
//        }
//    }
}

