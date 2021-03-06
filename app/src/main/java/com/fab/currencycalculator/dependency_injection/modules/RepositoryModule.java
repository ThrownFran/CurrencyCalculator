package com.fab.currencycalculator.dependency_injection.modules;



import com.fab.currencycalculator.data.authentification.AuthDataSource;
import com.fab.currencycalculator.data.authentification.AuthRepositoryImp;
import com.fab.currencycalculator.data.authentification.AuthStorageSource;
import com.fab.currencycalculator.data.authentification.SessionManagerImp;
import com.fab.currencycalculator.data.currency.CurrencyApi;
import com.fab.currencycalculator.data.currency.sources.CurrencyNetworkDataSource;
import com.fab.currencycalculator.data.currency.CurrencyRepositoryImp;
import com.fab.currencycalculator.data.currency.sources.CurrencyStaticDataSource;
import com.fab.currencycalculator.domain.repositories.AuthRepository;
import com.fab.currencycalculator.domain.repositories.CurrencyRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Singleton
    @Provides
    CurrencyRepository currencyRepo (CurrencyStaticDataSource staticDataSource,
                                     CurrencyNetworkDataSource networkDataSource) {
        return new CurrencyRepositoryImp(networkDataSource,staticDataSource);
    }

    @Singleton
    @Provides
    AuthRepository authRepo (AuthDataSource dataSource) {
        return new AuthRepositoryImp(dataSource);
    }

    @Singleton
    @Provides
    AuthDataSource authDataSource (SessionManagerImp storageManager) {
        return new AuthStorageSource(storageManager);
    }

    @Singleton
    @Provides
    CurrencyNetworkDataSource currencyDataSource (CurrencyApi api) {
        return new CurrencyNetworkDataSource(api);
    }

    @Singleton
    @Provides
    CurrencyStaticDataSource currencyStaticSource () {
        return new CurrencyStaticDataSource();
    }


}
