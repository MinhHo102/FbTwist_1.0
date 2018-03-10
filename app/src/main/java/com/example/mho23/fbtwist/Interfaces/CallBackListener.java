package com.example.mho23.fbtwist.Interfaces;

import com.example.mho23.fbtwist.data.ListItem;

import java.util.ArrayList;

/**
 * Created by mho23 on 2/11/18.
 *
 * Activities that want to be notified of results from onPostExecute of customTask class
 * must implement this interface.
 * The customTask class is responsible for fetching json data from a given url string.
 * the MenuOptions1 class implements this interface which allows it to interact with the data
 * from the onPostExecute method with the recyclerview that it instantiates. We're able to swap
 * empty arraylist with new arraylist from customTask and start detailed activities when each
 * listitem is clicked.
 *
 * "Basic observer pattern"
 */


public interface CallBackListener {
    /**
     * Function sends data retrieved from onPostExecute of customTask class back to activity
     * that implements the interface for usage, in this case, to swap out the intial empty arraylist
     * for the recyclerview with a new non empty arraylist.
     * */
    void callback(ArrayList<ListItem> data);
    /**
     * Function starts detailed activity each time a list item is clicked, sendign the name of the list
     * item through an intent.
     **/
    void startDetailedActivity(ListItem listItem, ArrayList<String> userOrders);
    /**
     * Function returns the arraylist of string initially passed from LoginSuccess Activity. This arraylist
     * records the user's orders among all menu activities to be displayed/finalized before POST/payment
     * intent at the Checkout Activity
     * */
    ArrayList<String> getUserOrders();
}
