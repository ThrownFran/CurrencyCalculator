package com.fab.currencycalculator.ui.home.di;

import com.fab.currencycalculator.dependency_injection.PresentationScope;
import com.fab.currencycalculator.ui.home.HomeFragment;

import dagger.BindsInstance;
import dagger.Subcomponent;

@PresentationScope
@Subcomponent(modules = {HomeModule.class})
public interface HomeComponent {
    void inject(HomeFragment viewImpl);

    @Subcomponent.Factory
    interface Factory{
        HomeComponent create(@BindsInstance HomeFragment viewImpl);
    }
}
