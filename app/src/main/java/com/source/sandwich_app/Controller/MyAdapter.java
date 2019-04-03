package com.source.sandwich_app.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.source.sandwich_app.Model.Sandwich;
import com.source.sandwich_app.R;
import com.source.sandwich_app.Vue.GalleryActivity;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<Sandwich> sandwichesList;
    private Activity activity;
    public static  final   String  image_name = "image_name";
    public static  final   String  image_url = "image_url";
    public static  final   String  image_provenance = "image_provenance";
    public static  final   String  image_prix = "image_prix";
    public static  final   String  image_pain = "image_pain";
    private MediaPlayer mediaPlayer=null;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public ImageView imageView;
        public ConstraintLayout parentLayout;
        public MyViewHolder(View v) {
            super(v);
            textView = v.findViewById(R.id.textView);
            imageView = v.findViewById(R.id.imageView);
            parentLayout = v.findViewById(R.id.parent_layout);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(Context context, List<Sandwich> sandwichesList, Activity activity) {
        this.activity = activity;
        this.sandwichesList = sandwichesList ;

    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_row, parent, false);

        return new MyViewHolder(itemView);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        Sandwich sandwich = sandwichesList.get(position);
        holder.textView.setText(sandwich.getName().substring(2,sandwich.getName().length()));
        Glide.with(this.activity)
                .load(sandwich.getUrl().substring(2,sandwich.getUrl().length()))
                .into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                    mediaPlayer = MediaPlayer.create(context.getApplicationContext(), R.raw.select_sound_effect);
                    mediaPlayer.start();
                Intent intent = new Intent(context, GalleryActivity.class);
                intent.putExtra(image_name, sandwichesList.get(position).getName());
                intent.putExtra(image_url, sandwichesList.get(position).getUrl());
                intent.putExtra(image_pain, sandwichesList.get(position).getPain());
                intent.putExtra(image_prix, sandwichesList.get(position).getPrix());
                intent.putExtra(image_provenance, sandwichesList.get(position).getProvenance());
                context.startActivity(intent);
                ((Activity) context).overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return sandwichesList.size();
    }
    public List<Sandwich> getSandwichesList(){
        return sandwichesList;
    }
}