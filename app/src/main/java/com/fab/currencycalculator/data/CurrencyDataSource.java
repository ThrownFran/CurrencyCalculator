package com.fab.currencycalculator.data;

import com.fab.currencycalculator.domain.use_cases.GetCurrencyRateUseCase;

import io.reactivex.Single;

public interface CurrencyDataSource {
    Single<GetCurrencyRateUseCase.Result> getCurrencyRate (GetCurrencyRateUseCase.Params params);
}
