package com.example.laba2.data;

import com.google.gson.annotations.SerializedName;

public class Recipe
{
    @SerializedName("Name")
    private String name;

    @SerializedName("Time")
    private int time;

    @SerializedName("Calorie")
    private int calorie;

    @SerializedName("Ingredients")
    private String ingredients;

    public Recipe(String name)
    {
        this.name = name;
    }

    public Recipe() {}

    public int getTime()
    {
        return time;
    }

    public void setTime(int time)
    {
        this.time = time;
    }

    public int getCalorie()
    {
        return calorie;
    }

    public void setCalorie(int calorie)
    {
        this.calorie = calorie;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getIngredients()
    {
        return ingredients;
    }

    public void setIngredients(String ingredients)
    {
        this.ingredients = ingredients;
    }

    @Override
    public String toString()
    {
        return "{" +
                "\"name\":" + '\"' + name + '\"' +
                ", \"time\":" + time +
                ", \"calorie\":" + calorie +
                ", \"ingredients\":" + '\"' + ingredients + '\"' +
                '}';
    }
}