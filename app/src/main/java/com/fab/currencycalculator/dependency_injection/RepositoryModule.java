package com.fab.currencycalculator.dependency_injection;


import com.fab.currencycalculator.data.CurrencyRepositoryImp;
import com.fab.currencycalculator.data.CurrencyDataSource;
import com.fab.currencycalculator.domain.repositories.CurrencyRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    CurrencyRepository currency (CurrencyDataSource dataSource) {
        return new CurrencyRepositoryImp(dataSource);
    }


}
