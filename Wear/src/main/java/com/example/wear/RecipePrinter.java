package com.example.wear;

import android.util.Log;

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
