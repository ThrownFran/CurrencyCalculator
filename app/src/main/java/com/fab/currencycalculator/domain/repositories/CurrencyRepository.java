package com.fab.currencycalculator.domain.repositories;

import com.fab.currencycalculator.domain.use_cases.GetCurrencyRateUseCase;
import com.fab.currencycalculator.domain.use_cases.GetGenericCurrencyRateUseCase;

import io.reactivex.Single;

public interface CurrencyRepository {
    Single<GetGenericCurrencyRateUseCase.Result> getCurrencyRate (GetGenericCurrencyRateUseCase.Params params);
    Single<GetCurrencyRateUseCase.Result> getCurrencyRate (GetCurrencyRateUseCase.Params params);
}
