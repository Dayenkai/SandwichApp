package com.source.sandwich_app.Controller;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestSandwichApi {

    @GET("sandwich.json")
    Call<RestSandwichResponse> getAllSandwich();
}
