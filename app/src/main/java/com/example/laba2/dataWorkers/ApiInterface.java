package com.example.laba2.dataWorkers;

import com.example.laba2.data.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("{recipes}.json")
    Call<List<Recipe>> getRecipes(@Path("recipes") String userName);
}