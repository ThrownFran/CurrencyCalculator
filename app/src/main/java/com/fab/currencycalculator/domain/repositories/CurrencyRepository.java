package com.fab.currencycalculator.domain.repositories;

import com.fab.currencycalculator.domain.use_cases.GetCurrencyRateUseCase;

import io.reactivex.Single;

public interface CurrencyRepository {
    Single<GetCurrencyRateUseCase.Result> getCurrencyRate (GetCurrencyRateUseCase.Params params);
}
