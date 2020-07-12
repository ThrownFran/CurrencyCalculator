package com.fab.currencycalculator.dependency_injection;

import android.content.Context;
import android.content.res.Resources;

import com.fab.currencycalculator.BaseApplication;
import com.fab.currencycalculator.data.currency.CurrencyApi;
import com.fab.currencycalculator.data.currency.CurrencyDataSource;
import com.fab.currencycalculator.data.currency.CurrencyNetworkDataSource;
import com.fab.currencycalculator.domain.schedulers.SchedulersFacade;
import com.fab.currencycalculator.ui.base.schedulers.SchedulersFacadeImp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    BaseApplication application;

    public ApplicationModule (BaseApplication application) {
        this.application = application;
    }

    @Singleton
    @Provides
    BaseApplication getApp () {
        return application;
    }

    @Singleton
    @Provides
    Context getApplicationContext () {
        return application.getApplicationContext();
    }

    @Singleton
    @Provides
    Resources getResources () {
        return application.getResources();
    }

    @Singleton
    @Provides
    SchedulersFacade getSchedulers () {
        return new SchedulersFacadeImp();
    }


}
