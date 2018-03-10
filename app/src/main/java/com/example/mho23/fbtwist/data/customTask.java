package com.example.mho23.fbtwist.data;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.example.mho23.fbtwist.Interfaces.CallBackListener;
import com.example.mho23.fbtwist.view.Tea;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by mho23 on 2/11/18.
 */

public class customTask extends AsyncTask<String, Void, ArrayList<ListItem>> {

    private CallBackListener listener;
    /**
     * Context is complaining about being leaked because we're passing in the context each time
     * we make a customTask. customTask will keep a reference to the activity until it has finished
     * its work in the background thread. If the activity finishes or is killed before the thread
     * finishes, the customTask will still have a reference to the activity, and therefore cannot
     * be garbage collected -- resulting in a memory leak.
     * */

    /**
    * Here we fixed the context leak by putting in a weakreference<activity_tea> field, but
     * when we need to extend this to other activities to use, like smoothies, it might be a problem
     * because we dont have a way to distinguish which activity to fetch in onPreExecute()
     * should we go back to having a context leak?
    **/
    private ProgressDialog pd;
//    Context context;
    private WeakReference<Tea> Tea_Activity;
//    private WeakReference<MenuOption2> Smoothie_Activity;

    public customTask(Activity activity) {
        Tea_Activity = new WeakReference<Tea>((Tea) activity);
    }

    //    public customTask(Context context) {
//        this.context = context;
//    }

//    public customTask(WeakReference<Context> context) {
//        this.context = context;
//    }

    public void setListener(CallBackListener listener) {
        this.listener = listener;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Activity activity = Tea_Activity.get();
        if (activity == null) return;
        pd = new ProgressDialog(activity);
        pd.setTitle("Fetching Menu");
        pd.setMessage("Downloading... Please wait");
        pd.show();
    }

    @Override
    protected ArrayList<ListItem> doInBackground(String... strings) {
        Calendar currentTime = Calendar.getInstance();
        Log.d("doInBackground function", " starts now" + currentTime.getTime());
        HttpURLConnection connection;
        ArrayList<ListItem> data = null;
        try {
            URL url1 = new URL(strings[0]);

            connection = (HttpURLConnection) url1.openConnection();
            connection.connect();
            InputStream inputStream = connection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            StringBuffer buffer = new StringBuffer();
            while ((line = reader.readLine()) != null) {

                buffer.append(line);
                String result = buffer.toString();
                JSONArray jsonArray = new JSONArray(result);
                data = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {

                    ListItem dataModel = new ListItem();
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    String name = jsonObject.getString("name");
                    dataModel.setItemName(name);
                    data.add(dataModel);

                }


            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return data;

    }

    @Override
    protected void onPostExecute(ArrayList<ListItem> listItems) {
        pd.dismiss();
        Calendar currentTime = Calendar.getInstance();
        Log.d("onPostExecute function", "starts now" + currentTime.getTime());
        super.onPostExecute(listItems);
        listener.callback(listItems);
    }
}

