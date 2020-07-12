package com.fab.currencycalculator.ui.main.di;


import com.fab.currencycalculator.dependency_injection.PresentationScope;
import com.fab.currencycalculator.ui.auth.LoginActivity;
import com.fab.currencycalculator.ui.auth.LoginContract;
import com.fab.currencycalculator.ui.auth.LoginPresenter;
import com.fab.currencycalculator.ui.main.MainActivity;
import com.fab.currencycalculator.ui.main.MainContract;
import com.fab.currencycalculator.ui.main.MainPresenter;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class MainModule {

    @PresentationScope
    @Binds
    abstract MainContract.Presenter presenter (MainPresenter presenter);

    @PresentationScope
    @Binds
    abstract MainContract.View view (MainActivity viewImpl);


}
