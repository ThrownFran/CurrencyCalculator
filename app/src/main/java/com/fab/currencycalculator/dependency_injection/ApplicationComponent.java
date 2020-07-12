package com.fab.currencycalculator.dependency_injection;

import com.fab.currencycalculator.ui.home.di.HomeComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, NetworkModule.class, RepositoryModule.class})
public interface ApplicationComponent {
    HomeComponent.Factory plusHome ();
}
