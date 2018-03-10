//package com.example.mho23.fbtwist.logic;
//
//import com.example.mho23.fbtwist.data.DataSourceInterface;
//import com.example.mho23.fbtwist.data.FakeDataSource;
//import com.example.mho23.fbtwist.data.ListItem;
//import com.example.mho23.fbtwist.view.MenuOption1;
//import com.example.mho23.fbtwist.view.ViewInterface;
//
///**
// * Created by mho23 on 2/7/18.
// */
//
//public class Controller {
//    private ViewInterface view;
//    private DataSourceInterface dataSource;
//    String url;
//
//    public Controller(ViewInterface view, DataSourceInterface dataSource, String url) {
//        this.view = view;
//        this.dataSource = dataSource;
//        this.url = url;
//        getListFromDataSource();
//    }
//
//    public Controller(MenuOption1 menuOption1, FakeDataSource dataSource, String url) {
//    }
//
//    public void getListFromDataSource() {
//        view.setUpAdapterAndView(
//                //ask the data source to get a list of data, and then give it to the view
//                dataSource.getListOfData(this.url)
//        );
//    }
//    public void onListItemClick(ListItem testItem) {
//        view.startDetailedActivity(
//                testItem.getItemName()
////                testItem.getItemPrice()
//        );
//    }
//}
