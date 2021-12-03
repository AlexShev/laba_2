package com.example.laba2;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.laba2.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity implements Navigatable
{
    private ActivitySecondBinding binding;

    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_recipes,
                R.id.navigation_description)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_second);

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

        NavigationUI.setupWithNavController(binding.navView, navController);
    }

    @Override
    public void navigate(int id)
    {
        navController.navigate(id);
    }
}
