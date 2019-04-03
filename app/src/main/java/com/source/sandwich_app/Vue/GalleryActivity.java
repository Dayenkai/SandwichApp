package com.source.sandwich_app.Vue;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.source.sandwich_app.R;
import com.source.sandwich_app.Controller.MyAdapter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

public class GalleryActivity extends AppCompatActivity implements Serializable {
    private    String  image_name = new String();
    private    String  image_url = image_name;
    private    String   image_provenance = image_name;
    private    String   image_pain = image_name;
    private    String   image_prix = image_name;

    private static final String TAG = "GalleryActivity";
    private static final String PREFS = "PREFS";
    private static final String PREFS_AGE = "PREFS_AGE";
    private static final String PREFS_NAME = "PREFS_NAME";
    SharedPreferences sharedPreferences;
    public static Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        context = this;
        Log.d(TAG, "onCreate started Gallery Activity.");
        getIncomingEvent();
        this.sharedPreferences = getBaseContext().getSharedPreferences(PREFS, MODE_PRIVATE);
        addSandwichToPreferences();
        final Button button = findViewById(R.id.list_item_gallery);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ThirdActivity.class);
                Log.d(TAG, "Dans list_item_button_listener");
                HashMap<String, LinkedHashSet<String>> sandwich = new HashMap<String, LinkedHashSet<String>>();
                //sandwich = (HashMap<String, LinkedHashSet<String>>)sharedPreferences.getAll();
                Map<String,?> keys = sharedPreferences.getAll();
                for(Map.Entry<String,?> entry : keys.entrySet()){
                    Log.d("map values",entry.getKey() + ": " + entry.getValue().toString());
                }
                intent.putExtra("prefered_sandwich", sandwich);
                context.startActivity(intent);
            }
        });
    }

    private void getIncomingEvent(){
        Intent intent = getIntent();
        String img_name = intent.getStringExtra(MyAdapter.image_name);
        String img_url = intent.getStringExtra(MyAdapter.image_url);
        String img_provenance = intent.getStringExtra(MyAdapter.image_provenance);
        String img_prix = intent.getStringExtra(MyAdapter.image_prix);
        String img_pain = intent.getStringExtra(MyAdapter.image_pain);


        this.image_name = img_name;
        this.image_url = img_url;
        this.image_provenance = img_provenance;
        this.image_prix = img_prix;
        this.image_pain = img_pain;


        /*Log.d(TAG, img_name);
        Log.d(TAG, img_url);
        Log.d(TAG, img_provenance);
        Log.d(TAG, img_pain);
        Log.d(TAG, img_prix);*/
        TextView name = findViewById(R.id.image_description);
        ImageView image = findViewById(R.id.imageView);
        name.setText(img_name.substring(2,img_name.length()));
        Glide.with(this)
                .load(img_url.substring(2,img_url.length()))
                .into(image);
    }

    public void addSandwichToPreferences()
    {
        final Button button = findViewById(R.id.button_gallery);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (!sharedPreferences.contains(image_name)) {
                    LinkedHashSet<String> sandwich_values = new LinkedHashSet<>();
                    sandwich_values.add(image_url);
                    sandwich_values.add(image_name);
                    sandwich_values.add(image_prix);
                    sandwich_values.add(image_provenance);
                    sandwich_values.add(image_pain);
                    //Log.d("map values",sandwich_values);
                    Iterator it = sandwich_values.iterator();

                    while(it.hasNext())

                        Log.d("map values",it.next().toString());
                    sharedPreferences
                            .edit()
                            //.putInt(PREFS_AGE, 1)
                            .putStringSet(image_name, sandwich_values)
                            .apply();
                    Toast.makeText(context, image_name + " Sauvegardé", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Le sandwich " + image_name + " est deja dans les preferences", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
    @Override
    public void finish()
    {
        super.finish();
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

}
