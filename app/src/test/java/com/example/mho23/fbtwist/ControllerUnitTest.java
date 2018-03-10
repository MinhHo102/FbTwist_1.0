package com.example.mho23.fbtwist;

import com.example.mho23.fbtwist.data.DataSourceInterface;
import com.example.mho23.fbtwist.data.ListItem;
import com.example.mho23.fbtwist.logic.Controller;
import com.example.mho23.fbtwist.view.ViewInterface;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(MockitoJUnitRunner.class)
public class ControllerUnitTest {

       /*
       * Testing controller class's logic using fake DataSource, and fake ViewInterface
       * */
    @Mock
    DataSourceInterface dataSource;

    @Mock
    ViewInterface view;

    Controller controller;

    private static final ListItem testItem = new ListItem(
            "thai tea",
            "$3.99"
    );

    @Before
    public void setUpTest() {
        //this line looks for "@Mock" and instanciates those (view and dataSource), then we can create the controller
        MockitoAnnotations.initMocks(this);
        controller = new Controller(view, dataSource);
    }
    @Test
    public void onGetListDataSuccessful() {
        //Set up any data we need for the Test
        ArrayList<ListItem> listOfData = new ArrayList<>();
        listOfData.add(testItem);
        //Set up mocks to return the data we want
        Mockito.when(dataSource.getListOfData())
                .thenReturn(listOfData);
        //call the method(Unit) we are Testing; Unit can be any bit of code, class or method or behavior of a class
        controller.getListFromDataSource();

        //Check how the tested class responds to the data it received or
        //test its behavior
        Mockito.verify(view).setUpAdapterAndView(listOfData);

    }
    @Test
    public void onListItemClicked() {
        controller.onListItemClick(testItem);

        Mockito.verify(view).startDetailedActivity(
                testItem.getItemName(),
                testItem.getItemPrice());
    }
}