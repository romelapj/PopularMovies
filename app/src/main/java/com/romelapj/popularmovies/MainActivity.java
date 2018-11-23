package com.romelapj.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.romelapj.popularmovies.ui.MainActivityDelegate;

import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavInflater;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity implements MainActivityDelegate {

    DrawerLayout drawerLayout;

    NavigationView navigationView;
    private Fragment navHostFragment;
    private NavController navController;
    private NavController navigationController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.navView);

        drawerLayout = findViewById(R.id.drawerLayout);
        drawerLayout.openDrawer(GravityCompat.START);

        FragmentManager fragmentManager = getSupportFragmentManager();
        navHostFragment = fragmentManager.findFragmentById(R.id.navHostFragment);
        navController = NavHostFragment.findNavController(navHostFragment);

        NavInflater inflater = navController.getNavInflater();
        NavGraph graph = inflater.inflate(R.navigation.nav_graph);
        navController.setGraph(graph);

        navigationController = Navigation.findNavController(this, R.id.navHostFragment);
        navigationController.onHandleDeepLink(getIntent());

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        navigationController.onHandleDeepLink(intent);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        return navigationController.navigateUp();
    }

    @Override
    public void setupNavDrawer(Toolbar toolbar) {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public void enableNavDrawer(Boolean enable) {
        drawerLayout.setEnabled(enable);
    }
}
