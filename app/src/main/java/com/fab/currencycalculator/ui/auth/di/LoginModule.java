package com.fab.currencycalculator.ui.auth.di;


import com.fab.currencycalculator.dependency_injection.PresentationScope;
import com.fab.currencycalculator.ui.auth.LoginContract;
import com.fab.currencycalculator.ui.auth.LoginPresenter;
import com.fab.currencycalculator.ui.auth.LoginActivity;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class LoginModule {

    @PresentationScope
    @Binds
    abstract LoginContract.Presenter presenter (LoginPresenter presenter);

    @PresentationScope
    @Binds
    abstract LoginContract.View view (LoginActivity viewImpl);


}
