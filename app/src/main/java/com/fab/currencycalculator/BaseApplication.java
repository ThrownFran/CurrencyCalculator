package com.fab.currencycalculator;

import android.app.Application;

import com.fab.currencycalculator.dependency_injection.ApplicationComponent;
import com.fab.currencycalculator.dependency_injection.modules.ApplicationModule;
import com.fab.currencycalculator.dependency_injection.DaggerApplicationComponent;

public class BaseApplication extends Application {

    private ApplicationComponent appComponent;
    private static BaseApplication instance;

    @Override
    public void onCreate () {
        instance = this;
        super.onCreate();
        initDagger();
    }

    private void initDagger () {
        appComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static BaseApplication getInstance () {
        return instance;
    }

    public ApplicationComponent getAppComponent () {
        return appComponent;
    }
}
