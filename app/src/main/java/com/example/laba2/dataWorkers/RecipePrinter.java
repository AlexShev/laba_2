package com.example.laba2.dataWorkers;

import android.util.Log;

import com.example.laba2.data.Recipe;

import java.util.List;

public class RecipePrinter
{
    public static void printRecipes(List<Recipe> recipes)
    {
        for (Recipe recipe : recipes)
        {
            Log.i("new Item", recipe.toString());
        }
    }
}
