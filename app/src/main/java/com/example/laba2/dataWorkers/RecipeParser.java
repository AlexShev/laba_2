package com.example.laba2.dataWorkers;

import com.example.laba2.data.Recipe;
import com.example.laba2.dataWorkers.ApiInterface;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RecipeParser
{
    public List<Recipe> parse(String[] recipes)
    {
        List<Recipe> users = new ArrayList<>(recipes.length);

        Recipe temp;

        for (int i = 0; i < recipes.length; i++)
        {
            temp = new Recipe(recipes[i]);

            users.add(temp);
        }

        return users;
    }

    public List<Recipe> parse(XmlPullParser xpp)
    {
        List<Recipe> users = new ArrayList<>();

        boolean status = true;
        Recipe currentRecipe = null;
        boolean inEntry = false;
        String textValue = "";

        try{
            int eventType = xpp.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT){

                String tagName = xpp.getName();
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        if("recipe".equalsIgnoreCase(tagName)){
                            inEntry = true;
                            currentRecipe = new Recipe();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(inEntry){
                            if("recipe".equalsIgnoreCase(tagName)){
                                users.add(currentRecipe);
                                inEntry = false;
                            } else if("name".equalsIgnoreCase(tagName)){
                                currentRecipe.setName(textValue);
                            }
                        }
                        break;
                    default:
                }
                eventType = xpp.next();
            }
        }
        catch (Exception e){
            status = false;
            e.printStackTrace();
        }

        return  users;
    }

    public Call<List<Recipe>> parse(String url)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiInterface apiInterface = retrofit.create(ApiInterface.class);

        return apiInterface.getRecipes("recipes");
    }
}
