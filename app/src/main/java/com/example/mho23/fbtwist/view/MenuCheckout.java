package com.example.mho23.fbtwist.view;

import android.app.Activity;
import android.content.ClipData;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.mho23.fbtwist.R;
import com.example.mho23.fbtwist.data.CheckoutAdapter;
import com.example.mho23.fbtwist.data.ItemOrdered;
import com.example.mho23.fbtwist.data.MyAdapter;
import com.example.mho23.fbtwist.data.customPostClass;
import com.example.mho23.fbtwist.data.customPostRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;

public class MenuCheckout extends Activity {

    //this is arraylist of strings of descriptions of items that user passed from menudetail(1,2,3,4...)
    private ArrayList<ItemOrdered> userOrders;
    private CheckoutAdapter adapter;
    private RecyclerView recyclerView;
    private int size;
    private Button confirm, cancel;
    //boolean to record whether it's safe to send userOrders to database.. that it's not empty
    boolean sharedPreferencesFinishedLoading = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_checkout);

        final String postURL = "http://10.0.2.2:8888/receiveCheckoutPost.php";
        //Size is the keyint value that we store to keep track of how many items user adds to checkout
        size = getKeyFromSharedPref();
//        System.out.println(size);
//        System.out.println("Before Load From Saved Pref is ran");
        LoadFromSharedPreferences();
        System.out.println("After Load From Saved Pref is ran");
        //First i want to use this arraylist of item ordered objects to display contents to user
        //then provide two buttons, cancel order and confirm.

        /**
         * When i eventually send data to database, i need to reset shared preferences values so
         * users can add future orders with a clean slate
         * https://stackoverflow.com/questions/3687315/deleting-shared-preferences
         * http://androidopentutorials.com/android-sharedpreferences-tutorial-and-example/
         * Also i might want to let user delete specific orders in which case i need to remove
         * the shared pref value using a certain key (2nd httml link)
         *
         * */
        buildRecyclerView();
        /**
         * What features to add?
         * Cancel entire order
         *  -delete parts of an order
         * confirm order (POST to Database two separate tables, both with the user's unique id
         * so they can retrieve past orders later and the other one for admin use.
         * admin use - display on webapp and ...
         * Save an order (as a favorite so they dont have to refill everytime)
         *  -save a part of an order
         *
         * Add checkout button to recyclerview activity
         * document better
         * refactor where necessary
         *
         * */
        confirm = (Button) findViewById(R.id.checkoutConfirm);
        cancel = (Button) findViewById(R.id.checkoutCancel);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToDatabase(postURL);
            }
        });
    }

    private void sendToDatabase(String url) {
        Log.d("function", "sendToDatabase: Starting");
        if (sharedPreferencesFinishedLoading == true) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(userOrders);
            System.out.println(json);
            customPostClass customPostClass = new customPostClass(url, json);
            //Instantiate a customPostRequest, passing in the url as a string
            customPostRequest customPostRequest = new customPostRequest(this);
            customPostRequest.execute(customPostClass);
        }
    }

    private void buildRecyclerView() {
        System.out.println("Running buildRecyclerView");
        recyclerView = (RecyclerView) findViewById(R.id.checkoutRecyclerView);
        recyclerView.setHasFixedSize(true);
        adapter = new CheckoutAdapter(this, userOrders);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        System.out.println("buildRecyclerView Ending");
    }

    private int getKeyFromSharedPref() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        return sharedPreferences.getInt("sharedPrefKey", -1);
    }


    /**
     * Loads all the items from shared preferences, preferably in arraylist form so we can use
     * a recycler/list view to display them in final checkout activity, and also will need to move
     * this function to that specific java file/activity.
     */

    private void LoadFromSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        //since we always increment the key, we should consider size - 1 instead
        for(int i = 0; i < size; i++) {
            String json = sharedPreferences.getString("CheckOut Item: " + i, null);
//            System.out.println(json);
            ItemOrdered itemOrdered = gson.fromJson(json, ItemOrdered.class);
//            System.out.println(json);
            if (userOrders == null) {
                userOrders = new ArrayList<>();
            }
            userOrders.add(itemOrdered);
        }
        //boolean check notes at very top
        sharedPreferencesFinishedLoading = true;
    }

}
