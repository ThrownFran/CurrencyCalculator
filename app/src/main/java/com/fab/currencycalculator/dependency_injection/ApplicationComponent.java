package com.fab.currencycalculator.dependency_injection;

import com.fab.currencycalculator.ui.auth.di.LoginComponent;
import com.fab.currencycalculator.ui.home.di.HomeComponent;
import com.fab.currencycalculator.ui.main.di.MainComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class,
        NetworkModule.class,
        RepositoryModule.class,
        SessionModule.class})
public interface ApplicationComponent {
    HomeComponent.Factory plusHome ();

    LoginComponent.Factory plusLogin ();

    MainComponent.Factory plusMain ();
}
