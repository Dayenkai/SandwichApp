package com.source.sandwich_app.Controller;

import android.util.Log;

import com.source.sandwich_app.Model.Sandwich;
import com.source.sandwich_app.Vue.Start1;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SandwichController {

    private Start1 view;
    public List<Sandwich>   listSandwich;

    public SandwichController(Start1 mainActivity)
    {
        this.view = mainActivity;
    }

    public void onCreate() {

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //On crée un objet retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/Dayenkai/sandwich/master/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //On crée notre instance de notre RestAPI Pokemon.
        RestSandwichApi restApi = retrofit.create(RestSandwichApi.class);


        Call<RestSandwichResponse> call = restApi.getAllSandwich();

        call.enqueue(new Callback<RestSandwichResponse>() {
            @Override
            public void onResponse(Call<RestSandwichResponse> call, Response<RestSandwichResponse> response) {
                RestSandwichResponse restFireEmblemResponse = response.body();
                listSandwich = restFireEmblemResponse.getSandwiches();
                view.showList(listSandwich);
                //System.exit(0);
            }

            @Override
            public void onFailure(Call<RestSandwichResponse> call, Throwable t) {
                Log.d("ERROR", "Api Error");
            }
        });

    }


}
