package com.example.laba2.dataWorkers;

import androidx.lifecycle.MutableLiveData;

import com.example.laba2.data.Recipe;
import com.example.laba2.modle.ContentController;
import com.example.laba2.resicleList.MyAdapter;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecipesLoader extends Thread
{
    public RecipesLoader(MutableLiveData<Boolean> isNormal, ContentController controller, String url, MyAdapter adapter)
    {
        this.isNormal = isNormal;
        this.controller = controller;
        this.url = url;
        this.adapter = adapter;
    }

    private static boolean isOdd = true;

    private MutableLiveData<Boolean> isNormal;
    private ContentController controller;
    private String url;
    private MyAdapter adapter;

    @Override
    public synchronized void run()
    {
        try
        {
            //sleep(10000);
            buildController();
        }
        catch (IOException e)
        {
            isNormal.setValue(false);

            e.printStackTrace();
        }
    }

    private void buildController() throws IOException
    {
        Call<List<Recipe>> call = new RecipeParser().parse(url);

        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response)
            {
                if (!response.isSuccessful())
                {
                    isNormal.setValue(false);
                }
                else
                {
                    List<Recipe> posts = response.body();

                    if (isOdd)
                    {
                        isOdd = false;

                        posts.sort(Comparator.comparingInt(a -> a.getName().length()));
                    }
                    else
                    {
                        isOdd = true;

                        posts.sort((a, b) -> Integer.compare(b.getName().length(), a.getName().length()));
                    }

                    controller.postRecipes(posts);

                    controller.setPosition(0);

                    RecipePrinter.printRecipes(posts);

                    isNormal.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t)
            {
                isNormal.setValue(false);
                t.printStackTrace();
            }
        });
    }
};

