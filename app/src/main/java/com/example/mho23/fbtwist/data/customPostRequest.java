package com.example.mho23.fbtwist.data;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.mho23.fbtwist.view.MenuCheckout;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by mho23 on 3/9/18.
 */

public class customPostRequest extends AsyncTask<customPostClass, Void, StringBuffer> {

    private WeakReference<MenuCheckout> MenuCheckoutActivity;
    private ProgressDialog pd;

    public customPostRequest(Activity menuCheckoutActivity) {
        MenuCheckoutActivity = new WeakReference<MenuCheckout>((MenuCheckout) menuCheckoutActivity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Activity activity = MenuCheckoutActivity.get();
        if (activity == null) return;
        pd = new ProgressDialog(activity);
        pd.setTitle("Sending to Database");
        pd.setMessage("Uploading... Please wait");
        pd.show();
    }

    @Override
    protected StringBuffer doInBackground(customPostClass... customPostClasses) {

        URL url = null;
        try {
            url = new URL(customPostClasses[0].getUrl());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        String json = customPostClasses[0].getJson();
//        System.out.println("Printing url and json contents: ");
//        System.out.println(url);
//        System.out.println(json);
        StringBuffer response = null;
        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();
            //Here we send data
            DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
            dataOutputStream.writeBytes(json);
            dataOutputStream.flush();
            dataOutputStream.close();
            //Next we get response
            InputStream inputStream = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String l;
            response = new StringBuffer();
            while ((l = bufferedReader.readLine()) != null) {
                response.append(l);
                response.append('\r');
            }
            bufferedReader.close();
//            System.out.println("response from server: " + response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    @Override
    protected void onPostExecute(StringBuffer stringBuffer) {
        super.onPostExecute(stringBuffer);
        pd.dismiss();
        Log.d("MenuCheckout", "onPostExecute: " + stringBuffer.toString());
        Toast.makeText(MenuCheckoutActivity.get(),"Order Sent", Toast.LENGTH_LONG).show();
    }
}
