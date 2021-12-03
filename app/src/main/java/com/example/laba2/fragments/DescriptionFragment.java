package com.example.laba2.fragments;

import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.laba2.R;
import com.example.laba2.databinding.FragmentDescriptionBinding;
import com.example.laba2.data.Recipe;
import com.example.laba2.modle.ContentController;

import java.util.List;

public class DescriptionFragment extends Fragment {

    private ContentController controller;
    private FragmentDescriptionBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        controller = new ViewModelProvider(requireActivity()).get(ContentController.class);

        binding = FragmentDescriptionBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView name = binding.textDashboard;
        final TextView kal = binding.kalorias;
        final TextView time = binding.time;
        final TextView ingredients = binding.ingridients;

        controller.getPosition().observe(getViewLifecycleOwner(), (Observer<Integer>) data -> {

            List<Recipe> recipes = controller.getRecipes().getValue();

            if (recipes == null || recipes.size() == 0)
            {
                name.setText("");
                kal.setText("");
                time.setText("");
                ingredients.setText("");
            }
            else
            {
                Recipe recipe = recipes.get(data);

                name.setText(recipe.getName());
                kal.setText(Html.fromHtml(getString(R.string.kalorias) + " " + recipe.getCalorie()));
                time.setText(Html.fromHtml(getString(R.string.time) + " " + recipe.getTime()));
                ingredients.setText(Html.fromHtml(getString(R.string.ingredients) + " " + recipe.getIngredients()));
            }
        });

        return root;
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
        binding = null;
    }
}