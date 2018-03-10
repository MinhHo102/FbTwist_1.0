package com.example.mho23.fbtwist.view;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mho23.fbtwist.R;
import com.facebook.accountkit.Account;
import com.facebook.accountkit.AccountKit;
import com.facebook.accountkit.AccountKitCallback;
import com.facebook.accountkit.AccountKitError;
import com.facebook.accountkit.PhoneNumber;

import java.util.ArrayList;

public class LoginSuccess extends Activity {

    /**
     * This is an ArrayList of Strings that we pass via intents to each Menu activity to store the user's
     * order before finally sending it to a checkout Activity to be displayed and confirmed to be sent
     * in a POST request to database (to be called by separate application to view order). It will
     * also be used to invoke payment implicit intent at the Checkout Activity.
    **/
    ArrayList<String> userOrders = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);

        //button for profile
        Button btnProfile = findViewById(R.id.btnProfile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Profile.class);
                startActivity(intent);
            }
        });
        //button for logout
        Button btnLogout = findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccountKit.logOut();
                //finish();
            }
        });
        //buttons for menu items
        Button btnMenuOption1 = findViewById(R.id.menuOption1);
        Button btnMenuOption2 = findViewById(R.id.menuOption2);
        Button btnMenuOption3 = findViewById(R.id.menuOption3);
        Button btnMenuOption4 = findViewById(R.id.menuOption4);
        initSharedPrefKey(0);
        btnMenuOption1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Tea.class);
                intent.putStringArrayListExtra("userOrders", userOrders);
                startActivity(intent);
            }
        });

    }

    private void initSharedPrefKey(int start) {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("sharedPrefKey", start);
        editor.commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

        AccountKit.getCurrentAccount(new AccountKitCallback<Account>() {
            @Override
            public void onSuccess(Account account) {
                // Get EditTexts
//                EditText etEmail, etPhone, etUserid;
//                etEmail = findViewById(R.id.etEmail);
//                etPhone = findViewById(R.id.etPhone);
//                etUserid = findViewById(R.id.etUserId);

                // Get phone number
                PhoneNumber phoneNumber = account.getPhoneNumber();
                String phoneNumberString = null;
                if (phoneNumber != null) {
                    phoneNumberString = phoneNumber.toString();
                }

                // Set all of these fields
//                etEmail.setText(String.format("User Email is %s", account.getEmail()));
//                etPhone.setText(String.format("User Phone is %s", phoneNumberString));
//                etUserid.setText(String.format("User Id is %s", account.getId()));
            }

            @Override
            public void onError(AccountKitError accountKitError) {
                //handle error somehow
            }
        });
    }
}

