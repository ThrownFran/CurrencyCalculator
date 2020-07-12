package com.fab.currencycalculator.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.fab.currencycalculator.BaseApplication;
import com.fab.currencycalculator.R;
import com.fab.currencycalculator.ui.Utils;
import com.fab.currencycalculator.ui.auth.LoginActivity;
import com.fab.currencycalculator.ui.base.BaseActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import butterknife.BindView;

public class MainActivity extends BaseActivity implements MainContract.View {


    @BindView(R.id.nav_view)
    BottomNavigationView bottomNavView;

    @Inject
    MainContract.Presenter presenter;

    @Override
    public int getLayout () {
        return R.layout.activity_main;
    }

    @Override
    protected void injectDependencies () {
        BaseApplication.getInstance().getAppComponent()
                .plusMain()
                .create(this)
                .inject(this);
    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onCreate();
        setupBottomNavView();
    }

    @Override
    protected void onDestroy () {
        presenter.onDestroy();
        super.onDestroy();
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

    @Override
    public void showUsername (String username) {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getString(R.string.welcome));
        stringBuilder.append(" ");
        stringBuilder.append(username);
        getSupportActionBar().setTitle(stringBuilder.toString());
    }

    @Override
    public void showGenericMessage (String errorMessage) {
        Utils.showToast(this,errorMessage);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        try {
            getMenuInflater().inflate(R.menu.menu_main, menu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_main_logout:
                clickLogout();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void clickLogout () {
        presenter.clickLogout();
    }

    @Override
    public void showMessage (String errorMessage) {
        Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToLogin () {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}