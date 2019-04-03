package com.source.sandwich_app.Vue;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.source.sandwich_app.Controller.MyAdapter;
import com.source.sandwich_app.Model.Sandwich;
import com.source.sandwich_app.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

public class ThirdActivity extends AppCompatActivity {

    public static Context context;
    HashMap<String, LinkedHashSet<String>> prefered_sandwich = new HashMap<>();
    public ArrayList<Sandwich> listSandwich = new ArrayList<>();
    private ThirdActivity view;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    MediaPlayer mediaPlayer = null;
    private static final String PREFS = "PREFS";
    SharedPreferences sharedPreferences;
    private static final String TAG = "GalleryActivity";
    int pos=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Log.d(TAG, "onCreate started Preferences Activity.");
        Intent intent = getIntent();
        prefered_sandwich = (HashMap<String, LinkedHashSet<String>>)intent.getSerializableExtra("prefered_sandwich");
        view = this;
        this.sharedPreferences = getBaseContext().getSharedPreferences(PREFS, MODE_PRIVATE);
        Log.d(TAG, "Dans third Activity");
        //Log.v("HashMapTest", prefered_sandwich.get());
        for(Map.Entry<String, LinkedHashSet<String>> entry : prefered_sandwich.entrySet()) {
            HashSet<String> jeej = entry.getValue();
            Iterator<String> iterator = jeej.iterator();
            Sandwich sandwich = new Sandwich();
            while (iterator.hasNext()) {
                String myCurrentElement = iterator.next();
                switch(myCurrentElement.charAt(0))
                {
                    case 'n':
                        sandwich.setName(myCurrentElement);
                        break;
                    case 'p':
                        sandwich.setPain(myCurrentElement);
                        break;
                    case 'f':
                        sandwich.setProvenance(myCurrentElement);
                        break;
                    case 'c':
                        sandwich.setPrix(myCurrentElement);
                        break;
                    case 'u':
                        sandwich.setUrl(myCurrentElement);
                        break;

                }
                Log.d("map values",myCurrentElement);
            }
            if (sandwich == null)
                Log.d(TAG, "Sandwich add failed");
            else {
                Log.d(TAG, "Le sandwich "+sandwich.getName()+" venant de "+sandwich.getProvenance()+" coute "+sandwich.getPrix()+ " dont la fourniture est"+sandwich.getPain());
                listSandwich.add(sandwich);
            }
            recyclerView = findViewById(R.id.my_recycler_view_second);
            showList(listSandwich);
        }
        final Button button = findViewById(R.id.clear_prefered);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                if (sharedPreferences.getAll().size() != 0) {
                    editor.clear().commit();
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.classy_restaurant);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                    Toast.makeText(context, "Tous les sandwich ont été supprimés", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
