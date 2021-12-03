package com.example.laba2.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.laba2.Navigatable;
import com.example.laba2.R;
import com.example.laba2.dataWorkers.RecipesLoader;
import com.example.laba2.modle.ContentController;
import com.example.laba2.resicleList.MyAdapter;

public class RecipeFragment extends Fragment
{
    private ContentController controller;
    private MutableLiveData<Boolean> isNormal = new MutableLiveData<>(true);
    private MyAdapter adapter;

    private SwipeRefreshLayout mySwipeRefreshLayout;

    private final static String url =
            "https://raw.githubusercontent.com/Pec3maker/android_labs/main/JsonLab/";

    private void onStateChanged(Boolean state)
    {
        if (!state)
        {
            Toast.makeText(RecipeFragment.this.getActivity(), "проблема", Toast.LENGTH_SHORT).show();
        }
    }

    private final MyAdapter.ItemClickListener itemClickListener = new MyAdapter.ItemClickListener()
    {
        @Override
        public void onItemClick(View view, int position)
        {
            controller.setPosition(position);

            ((Navigatable) RecipeFragment.this.getActivity()).navigate(R.id.navigation_description);
        }
    };

    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        controller = new ViewModelProvider(requireActivity()).get(ContentController.class);

        isNormal.observe(getViewLifecycleOwner(), this::onStateChanged);

        View root = inflater.inflate(R.layout.fragment_recipes, container, false);

        mySwipeRefreshLayout = root.findViewById(R.id.mySwipeRefreshLayout);

        RecyclerView recyclerView = root.findViewById(R.id.my_recycler_view);
        GridLayoutManager layout = new GridLayoutManager(this.getActivity(), 2);
        recyclerView.setLayoutManager(layout);

        adapter = new MyAdapter(controller.getRecipes().getValue());

        adapter.setClickListener(itemClickListener);

        recyclerView.setAdapter(adapter);

        controller.getRecipes().observe(getViewLifecycleOwner(), adapter::setRecipes);

        if (!controller.isBuilt())
        {
            // чтение рецептов из интернета
            new RecipesLoader(isNormal, controller, url, adapter).start();
        }

        mySwipeRefreshLayout.setOnRefreshListener(() -> {
            mySwipeRefreshLayout.setRefreshing(true);

            // чтение рецептов из интернета
            Thread runnable = new RecipesLoader(isNormal, controller, url, adapter);
            runnable.start();

            mySwipeRefreshLayout.setRefreshing(false);
        });

        return root;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }
}