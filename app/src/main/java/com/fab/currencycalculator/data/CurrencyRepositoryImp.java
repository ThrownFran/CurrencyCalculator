package com.fab.currencycalculator.data;

import com.fab.currencycalculator.domain.repositories.CurrencyRepository;
import com.fab.currencycalculator.domain.use_cases.GetCurrencyRateUseCase;
import com.fab.currencycalculator.domain.use_cases.GetGenericCurrencyRateUseCase;

import javax.inject.Inject;

import io.reactivex.Single;

public class CurrencyRepositoryImp implements CurrencyRepository {


    private CurrencyDataSource dataSource;

    @Inject
    public CurrencyRepositoryImp (CurrencyDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Single<GetGenericCurrencyRateUseCase.Result> getCurrencyRate (GetGenericCurrencyRateUseCase.Params params) {
        return dataSource.getCurrencyRate(params);
    }

    @Override
    public Single<GetCurrencyRateUseCase.Result> getCurrencyRate (GetCurrencyRateUseCase.Params params) {
        return dataSource.getCurrencyRate(params);
    }
}
