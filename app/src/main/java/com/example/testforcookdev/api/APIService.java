package com.example.testforcookdev.api;

import com.example.testforcookdev.model.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    @GET("cookdev/get_recipes.php")
    Call<List<Recipe>> getCooks(@Query("category") int category);

}
