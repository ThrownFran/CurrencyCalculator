package com.fab.currencycalculator.data.currency;

import com.fab.currencycalculator.domain.models.BS;
import com.fab.currencycalculator.domain.models.Currency;
import com.fab.currencycalculator.domain.models.Petro;
import com.fab.currencycalculator.domain.repositories.CurrencyRepository;
import com.fab.currencycalculator.domain.use_cases.GetCurrencyRateUseCase;

import javax.inject.Inject;

import io.reactivex.Single;

public class CurrencyRepositoryImp implements CurrencyRepository {


    private CurrencyNetworkDataSource networkDataSource;
    private CurrencyStaticDataSource staticDataSource;

    @Inject
    public CurrencyRepositoryImp (CurrencyNetworkDataSource networkDataSource,
                                  CurrencyStaticDataSource staticDataSource) {
        this.networkDataSource = networkDataSource;
        this.staticDataSource = staticDataSource;
    }

    @Override
    public Single<GetCurrencyRateUseCase.Result> getCurrencyRate
            (GetCurrencyRateUseCase.Params params) {

        if(isRareCurrency(params.currency)){
            return staticDataSource.getCurrencyRate(params);
        }

        return networkDataSource.getCurrencyRate(params);
    }

    //Not easily available in network
    private boolean isRareCurrency (Currency currency) {
        return currency instanceof Petro || currency instanceof BS;
    }
}
