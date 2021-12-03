package com.example.wear;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.wear.widget.WearableLinearLayoutManager;
import androidx.wear.widget.WearableRecyclerView;

import com.example.wear.databinding.RecycleBinding;
import com.example.wear.resicleList.MyAdapter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecycleList extends Activity {

    private WearableRecyclerView mRecyclerView;
    private RecycleBinding binding;
    private MutableLiveData<Boolean> isNormal = new MutableLiveData<>(true);
    private MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

//        isNormal.observe(this, new Observer<Boolean>() {
//            @Override
//            public void onChanged(Boolean aBoolean)
//            {
//                if (!aBoolean)
//                {
//                    Toast.makeText(RecycleList.this, "проблема", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

        binding = RecycleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mRecyclerView = binding.recyclerView;

        adapter = new MyAdapter(new ArrayList<>());

        getList("https://raw.githubusercontent.com/Pec3maker/android_labs/main/JsonLab/");

        mRecyclerView.setLayoutManager(new
                WearableLinearLayoutManager(RecycleList.this));
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setCircularScrollingGestureEnabled(true);
        mRecyclerView.setEdgeItemsCenteringEnabled(true);
    }

    private void getList(String url)
    {
        Call<List<Recipe>> call = new RecipeParser().parse(url);

        Log.i("new Item", "start reading");

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
                    Log.i("new Item", "start reading");


                    List<Recipe> posts = response.body();

                    RecipePrinter.printRecipes(posts);

                    adapter.setItems(posts);

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


        String[] res = getResources().getStringArray(R.array.recipes);
        List<Recipe> temp = new ArrayList<>(res.length);

        for (int i = 0; i < res.length; i++)
        {
            temp.add(new Recipe(res[i]));
        }

        adapter.setItems(temp);
    }


}