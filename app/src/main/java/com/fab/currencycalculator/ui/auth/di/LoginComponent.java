package com.fab.currencycalculator.ui.auth.di;

import com.fab.currencycalculator.dependency_injection.PresentationScope;
import com.fab.currencycalculator.ui.auth.LoginActivity;

import dagger.BindsInstance;
import dagger.Subcomponent;

@PresentationScope
@Subcomponent(modules = {LoginModule.class})
public interface LoginComponent {
    void inject (LoginActivity viewImpl);

    @Subcomponent.Factory
    interface Factory{
        LoginComponent create (@BindsInstance LoginActivity viewImpl);
    }
}
