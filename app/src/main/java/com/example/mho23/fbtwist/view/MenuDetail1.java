package com.example.mho23.fbtwist.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.TextView;
import com.example.mho23.fbtwist.R;
import com.example.mho23.fbtwist.data.ItemOrdered;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class MenuDetail1 extends Activity {

    private static final String EXTRA_NAME = "EXTRA_NAME";
    private static final String EXTRA_TYPE = "EXTRA_TYPE";

    //Arraylist of string that will store user orders and send it to checkoutActivity to display
    //and processing (post request)
    private ArrayList<String> userOrder = new ArrayList<>();
    private TextView name;
    private Button jelly, boba;
    private Button addToOrder, checkOut;
    //Tapioca
    private CheckBox tapioca;
    //Jelly
    private TextView ListOfJelly;
    private String[] JellyArray;
    private boolean[] JellyChecked;
    private ArrayList<Integer> userSelectedList = new ArrayList<>();
    //Boba
    private TextView ListOfBoba;
    private String[] BobaArray;
    private boolean[] BobaChecked;
    private ArrayList<Integer> userSelectedList2 = new ArrayList<>();
    //RadioButtons
    private RadioButton milk, fruit, regular, large;
    //String description we create and keep track of what jelly/boba user adds to item be4 POST
//    private StringBuffer Description = new StringBuffer();
//    ArrayList<> userOrders = new ArrayList<>();
    private ArrayList<ItemOrdered> userOrders = new ArrayList<>();
    private Button backbutton;
    //String to keep track of key for sharedpreferences and we can increment its value too

    private String sharedPrefString = "CheckOut Item: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_detail1);

        /*I wouldn't normally pass all this Data via Intent, so understand that this is just a quick
        implementation to get things working for the Demo. I'd normally pass just a Unique id as an
        extra, and then retrieve the appropriate Data from a Service.*/
        //look into "class ListItem implements parcelable" and unique id thing

        Intent intent = getIntent();
        final String nameExtra = intent.getStringExtra(EXTRA_NAME);
        final String typeExtra = intent.getStringExtra(EXTRA_TYPE);
//        userOrders = intent.getStringArrayListExtra("userOrders");

        //
        name = (TextView) findViewById(R.id.text_detail_activity_name);
        name.setText(nameExtra);
        //Jelly
        JellyArray = getResources().getStringArray(R.array.typesOfJelly);
        JellyChecked = new boolean[JellyArray.length];
        jelly = (Button) findViewById(R.id.btnJelly);
        ListOfJelly = (TextView) findViewById(R.id.JellySelected);
        //Boba
        BobaArray = getResources().getStringArray(R.array.typesOfBoba);
        BobaChecked = new boolean[BobaArray.length];
        boba = (Button) findViewById(R.id.btnBoba);
        ListOfBoba = (TextView) findViewById(R.id.BobaSelected);
        //Button for jelly dialog
        jelly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder jellyDialog = new AlertDialog.Builder(MenuDetail1.this);
                jellyDialog.setTitle(R.string.jelly);
                jellyDialog.setMultiChoiceItems(JellyArray, JellyChecked, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                if (isChecked) {
                                    //if user selected item, add it to the list
                                    userSelectedList.add(which);
                                } else if (userSelectedList.contains(which)) {
                                    //else if item is already in array, remove it
                                    userSelectedList.remove(Integer.valueOf(which));
                                }
                            }
                        });


                        jellyDialog.setCancelable(false);
                        //set positive button
                        jellyDialog.setPositiveButton(R.string.jellyPositive, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //// User clicked OK, so save the mSelectedItems results somewhere
                                // or return them to the component that opened the dialog
                                String item = "";
                                for(int i = 0; i < userSelectedList.size(); i++) {
                                    item = item + JellyArray[userSelectedList.get(i)];
                                    if (i != userSelectedList.size()-1 )
                                        item += ", ";
                                }
                                ListOfJelly.setText(item);
                            }
                        });
                        //set negative button
                        jellyDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (userSelectedList.size() > 0){
                                    for(int i = 0; i < JellyChecked.length; i++){
                                        JellyChecked[i] = false;
                                        userSelectedList.clear();
                                        userSelectedList = new ArrayList<>();
                                    }
                                    dialog.dismiss();
                                }
                                dialog.dismiss();
                            }
                        });
                        jellyDialog.setNeutralButton(R.string.neutral, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                for(int i = 0; i < JellyChecked.length; i++){
                                    JellyChecked[i] = false;
                                    userSelectedList.clear();
                                    ListOfJelly.setText("");
                                }
                            }
                        });
                AlertDialog returnDialog = jellyDialog.create();
                returnDialog.show();
            }
        });
        //Button for Boba dialog
        boba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder bobaDialog = new AlertDialog.Builder(MenuDetail1.this);
                bobaDialog.setTitle(R.string.boba);
                bobaDialog.setMultiChoiceItems(BobaArray, BobaChecked, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            //if user selected item, add it to the list
                            userSelectedList2.add(which);
                        } else if (userSelectedList2.contains(which)) {
                            //else if item is already in array, remove it
                            userSelectedList2.remove(Integer.valueOf(which));
                        }
                    }
                });
                    bobaDialog.setCancelable(false);
                    bobaDialog.setPositiveButton(R.string.jellyPositive, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String item = "";
                            for(int i = 0; i < userSelectedList2.size(); i++) {
                                item = item + BobaArray[userSelectedList2.get(i)];
                                if (i != userSelectedList2.size()-1 )
                                    item += ", ";
                            }
                            ListOfBoba.setText(item);
                        }
                    });
                    bobaDialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (userSelectedList2.size() > 0){
                                for(int i = 0; i < BobaChecked.length; i++){
                                    BobaChecked[i] = false;
                                    userSelectedList2.clear();
                                    userSelectedList2 = new ArrayList<>();
                                }
                                dialog.dismiss();
                            }
                            dialog.dismiss();
                        }
                    });
                    bobaDialog.setNeutralButton(R.string.neutral, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            for(int i = 0; i < BobaChecked.length; i++){
                                BobaChecked[i] = false;
                                userSelectedList2.clear();
                                ListOfBoba.setText("");
                            }
                        }
                    });
                AlertDialog returnDialog = bobaDialog.create();
                returnDialog.show();
            }
        });


        //Buttons to add customized item to checkout basket, and compact it into items (menuitem class) before POST


    addToOrder = (Button) findViewById(R.id.btnAddToOrder);
    checkOut = (Button) findViewById(R.id.btnCheckOut);

    //Radiobuttons
    milk = (RadioButton) findViewById(R.id.rbmilkTea);
    fruit = (RadioButton) findViewById(R.id.rbfruitTea);
    regular = (RadioButton) findViewById(R.id.sizeReg);
    large = (RadioButton) findViewById(R.id.sizeLarge);
    tapioca = (CheckBox) findViewById(R.id.checkboxTapioca);

        addToOrder.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            ItemOrdered itemOrdered = new ItemOrdered();
            itemOrdered.setName(nameExtra);
            itemOrdered.setType(typeExtra.toUpperCase());
            if (regular.isChecked()) itemOrdered.setSize("REGULAR");
            else itemOrdered.setSize("LARGE");

            if (milk.isChecked()) itemOrdered.setTeaType(true);
            else itemOrdered.setTeaType(false);

            if (tapioca.isChecked()) itemOrdered.setTapioca(true);
            else itemOrdered.setTapioca(false);

            ArrayList<String> BobaArrayList = new ArrayList<>();
            for(int i = 0; i < BobaArray.length; i++) {
                if (BobaChecked[i])
                    BobaArrayList.add(BobaArray[i]);
            }
            ArrayList<String> JellyArrayList = new ArrayList<>();
            for(int i = 0; i < JellyArray.length; i++) {
                if (JellyChecked[i])
                    JellyArrayList.add(JellyArray[i]);
            }
            //Can this be done more efficiently?
            itemOrdered.setJelly(JellyArrayList);
            itemOrdered.setBoba(BobaArrayList);


            int sharedPrefKey = GetSharedPrefKey();
            System.out.println(GetSharedPrefKey());
            SaveToSharedPreferences(itemOrdered, sharedPrefKey);

            SaveSharedPrefKey(sharedPrefKey);

            System.out.println(GetSharedPrefKey());
        }
    });
        checkOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuDetail1.this, MenuCheckout.class);
//                intent.putStringArrayListExtra("userOrders", userOrders);
                //I need to pass the sharedPrefKeyInt so i know how many items to retrieve 0...this number
//                intent.putExtra("sharedPrefKeyInt", sharedPrefKey);
                startActivity(intent);
            }
        });
//        backbutton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(MenuDetail1.this, activity_tea.class);
////                intent.putStringArrayListExtra("userOrders", userOrders);
//                startActivity(intent);
//            }
//        });
    }

    private void SaveSharedPrefKey(int sharedPrefKey) {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("sharedPrefKey", sharedPrefKey+1);
        editor.commit();
    }

    private int GetSharedPrefKey() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        return sharedPreferences.getInt("sharedPrefKey", -1);
    }

    /**
     * Function that saves user order data in arraylist<ItemOrdered> format to shared preferences
     * to be called for display in checkout activity and to be sent to database?
     * */
    private void SaveToSharedPreferences(ItemOrdered order, int sharedPrefKey) {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(order);
        System.out.println(json);
        String sharedPrefKeyFinal = sharedPrefString + sharedPrefKey;
        editor.putString(sharedPrefKeyFinal, json);
        editor.commit();
    }


}
