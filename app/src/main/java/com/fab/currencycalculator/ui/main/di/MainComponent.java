package com.fab.currencycalculator.ui.main.di;

import com.fab.currencycalculator.dependency_injection.PresentationScope;
import com.fab.currencycalculator.ui.auth.LoginActivity;
import com.fab.currencycalculator.ui.main.MainActivity;

import dagger.BindsInstance;
import dagger.Subcomponent;

@PresentationScope
@Subcomponent(modules = {MainModule.class})
public interface MainComponent {
    void inject (MainActivity viewImpl);

    @Subcomponent.Factory
    interface Factory{
        MainComponent create (@BindsInstance MainActivity viewImpl);
    }
}
