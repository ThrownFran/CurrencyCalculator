package com.fab.currencycalculator.dependency_injection;

import com.fab.currencycalculator.dependency_injection.modules.ApplicationModule;
import com.fab.currencycalculator.dependency_injection.modules.BusModule;
import com.fab.currencycalculator.dependency_injection.modules.NetworkModule;
import com.fab.currencycalculator.dependency_injection.modules.ParserModule;
import com.fab.currencycalculator.dependency_injection.modules.RepositoryModule;
import com.fab.currencycalculator.dependency_injection.modules.SessionModule;
import com.fab.currencycalculator.ui.auth.di.LoginComponent;
import com.fab.currencycalculator.ui.base.ErrorMessageFactory_Factory;
import com.fab.currencycalculator.ui.home.di.HomeComponent;
import com.fab.currencycalculator.ui.main.di.MainComponent;
import com.fab.currencycalculator.ui.qr_reader.di.QrReaderComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class,
        NetworkModule.class,
        RepositoryModule.class,
        SessionModule.class,
        BusModule.class,
        ParserModule.class})
public interface ApplicationComponent {
    HomeComponent.Factory plusHome ();

    LoginComponent.Factory plusLogin ();

    MainComponent.Factory plusMain ();

    QrReaderComponent.Factory plusQrReader ();
}
