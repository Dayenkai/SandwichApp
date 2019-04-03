package com.source.sandwich_app.Vue;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.source.sandwich_app.Controller.MyAdapter;
import com.source.sandwich_app.Controller.SandwichController;
import com.source.sandwich_app.Model.Sandwich;
import com.source.sandwich_app.R;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;

public class Start1 extends AppCompatActivity {

    private static final String TOTAL_COUNT = "total_count";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private SandwichController  controller;
    private static final String PREFS = "PREFS";
    SharedPreferences sharedPreferences;
    public static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new SandwichController(this);
        controller.onCreate();
        recyclerView = findViewById(R.id.my_recycler_view);
        ImageView background = (ImageView) findViewById(R.id.parent_layout);
        this.sharedPreferences = getBaseContext().getSharedPreferences(PREFS, MODE_PRIVATE);
        final Button button = findViewById(R.id.list_item_gallery);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ThirdActivity.class);
                HashMap<String, LinkedHashSet<String>> sandwich = (HashMap<String, LinkedHashSet<String>>)sharedPreferences.getAll();
                intent.putExtra("prefered_sandwich", sandwich);
                context.startActivity(intent);
            }
        });
        //keej.onTokenRefresh();
        //MessageReceiver remoteMessage = new MessageReceiver();
        /*String title = remoteMessage.getNotification().getTitle();
        String message = remoteMessage.getNotification().getBody();*/
        //PendingInt
        // ent pendingIntent = PendingIntent.getActivity(this, REQUEST_CODE, i, PendingIntent.FLAG_UPDATE_CURRENT);
    }
    public void showList(List<Sandwich> listSandwiches)
    {
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // specify an adapter (see also next example)
        recyclerView.setHasFixedSize(true);
        mAdapter = new MyAdapter(this, listSandwiches, this);
        recyclerView.setAdapter(mAdapter);
    }
}
