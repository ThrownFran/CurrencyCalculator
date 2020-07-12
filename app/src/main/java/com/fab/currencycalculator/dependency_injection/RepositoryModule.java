package com.fab.currencycalculator.dependency_injection;



import com.fab.currencycalculator.data.authentification.AuthDataSource;
import com.fab.currencycalculator.data.authentification.AuthRepositoryImp;
import com.fab.currencycalculator.data.authentification.AuthStorageSource;
import com.fab.currencycalculator.data.authentification.StorageManager;
import com.fab.currencycalculator.data.currency.CurrencyApi;
import com.fab.currencycalculator.data.currency.CurrencyNetworkDataSource;
import com.fab.currencycalculator.data.currency.CurrencyRepositoryImp;
import com.fab.currencycalculator.data.currency.CurrencyDataSource;
import com.fab.currencycalculator.data.currency.CurrencyStaticDataSource;
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
    AuthDataSource authDataSource (StorageManager storageManager) {
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
