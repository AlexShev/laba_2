package com.example.laba2.modle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.laba2.data.Recipe;

import java.util.ArrayList;
import java.util.List;

public class ContentController extends ViewModel
{
    private final MutableLiveData<Integer> position;
    private final MutableLiveData<List<Recipe>> recipes;

    private boolean isBuilt = false;

    public ContentController()
    {
        recipes = new MutableLiveData<>();
        position = new MutableLiveData<>();
        position.setValue(0);
        
        this.recipes.setValue(new ArrayList<>());
    }

    public void setPosition(int position)
    {
        this.position.setValue(position);
    }

    public void postRecipes(List<Recipe> recipes)
    {
        isBuilt = true;
        this.recipes.postValue(recipes);
    }

    public MutableLiveData<Integer> getPosition()
    {
        return position;
    }

    public MutableLiveData<List<Recipe>> getRecipes()
    {
        return recipes;
    }

    public boolean isBuilt()
    {
        return isBuilt;
    }
}
