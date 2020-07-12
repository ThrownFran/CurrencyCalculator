package com.fab.currencycalculator.dependency_injection;



import android.content.Context;

import com.fab.currencycalculator.data.authentification.AuthDataSource;
import com.fab.currencycalculator.data.authentification.AuthRepositoryImp;
import com.fab.currencycalculator.data.authentification.SessionManagerImp;
import com.fab.currencycalculator.data.currency.CurrencyApi;
import com.fab.currencycalculator.data.currency.CurrencyNetworkDataSource;
import com.fab.currencycalculator.data.currency.CurrencyRepositoryImp;
import com.fab.currencycalculator.data.currency.CurrencyStaticDataSource;
import com.fab.currencycalculator.domain.SessionManager;
import com.fab.currencycalculator.domain.repositories.AuthRepository;
import com.fab.currencycalculator.domain.repositories.CurrencyRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SessionModule {

    @Singleton
    @Provides
    SessionManager session (Context context) {
        return new SessionManagerImp(context);
    }


}
