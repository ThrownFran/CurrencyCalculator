package com.fab.currencycalculator.ui;

import android.os.Bundle;

import com.fab.currencycalculator.R;
import com.fab.currencycalculator.ui.base.BaseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import butterknife.BindView;

public class MainActivity extends BaseActivity {


    @BindView(R.id.nav_view)
    BottomNavigationView bottomNavView;

    @Override
    public int getLayout () {
        return R.layout.activity_main;
    }

    @Override
    protected void injectDependencies () {
        //TODO
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupBottomNavView();
    }

    private void setupBottomNavView () {
        setNavControllerInToolbar();
        setNavControllerInBottomView();
    }

    private void setNavControllerInToolbar () {
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration
                .Builder(R.id.navigation_home,
                R.id.navigation_qr_reader)
                .build();

        NavigationUI.setupActionBarWithNavController(this, getNavController(), appBarConfiguration);
    }

    private void setNavControllerInBottomView () {
        NavigationUI.setupWithNavController(bottomNavView, getNavController());
    }

    public NavController getNavController () {
        return Navigation.findNavController(this, R.id.nav_host_fragment);
    }

}