package com.example.mho23.fbtwist.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import com.example.mho23.fbtwist.R;
import com.example.mho23.fbtwist.Interfaces.CallBackListener;
import com.example.mho23.fbtwist.data.ListItem;
import com.example.mho23.fbtwist.data.MyAdapter;
import com.example.mho23.fbtwist.data.customTask;

import java.util.ArrayList;

public class Tea extends Activity implements CallBackListener{

    private static final String EXTRA_NAME = "EXTRA_NAME";
    private static final String EXTRA_TYPE = "EXTRA_TYPE";
    private ArrayList<ListItem> listOfData = new ArrayList<>(0);
    private MyAdapter adapter;
    String jsonURL="http://10.0.2.2:8888/menuConnect.php";
    private RecyclerView recyclerView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tea);
//        button = (Button) findViewById(R.id.btnMenuOptionBack);
//        Calendar currentTime = Calendar.getInstance();
//        Log.d("onCreateStart", "onCreateStart: starts now" + currentTime.getTime());
        /**
         * Here we are setting up the recyclerView with an empty set of data and updating it after
         * executing the customTask.
         * */
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewMenu1);
        recyclerView.setHasFixedSize(true);
        adapter = new MyAdapter(this, listOfData, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        /**
         * By calling the customTask in this activity, we are providing a reference to this activity
         * to the customTask. In the onPostExecute method of the customTask,
         * it will call the methods (below) that we override by implementing the interface.
         * */
        customTask mytask = new customTask(this);
        mytask.setListener(this);

//        Calendar currentTime2 = Calendar.getInstance();
//        Log.d("BeforeExecute", "BeforeExecute starts" + currentTime2.getTime());

        mytask.execute(jsonURL);


    }

    @Override
    public void callback(ArrayList<ListItem> data) {
        adapter.swap(data);
    }
    @Override
    public void startDetailedActivity(ListItem item, ArrayList<String> userOrders) {
        Intent intent = new Intent(this, MenuDetail1.class);
        String name = item.getItemName();
        intent.putExtra(EXTRA_NAME, name);
        String itemType = Tea.class.toString();
        intent.putExtra(EXTRA_TYPE, itemType);
        intent.putStringArrayListExtra("userOrders", userOrders);
        startActivity(intent);
    }

    @Override
    public ArrayList<String> getUserOrders() {
        Intent intent = getIntent();
        ArrayList<String> ret = intent.getStringArrayListExtra("userOrders");
//        Log.d("getUserOrders function", "is working: " + ret.get(0));
        return ret;
    }
}