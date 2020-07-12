package com.fab.currencycalculator.data;

import com.fab.currencycalculator.domain.models.RateModel;
import com.fab.currencycalculator.domain.use_cases.GetCurrencyRateUseCase;

import java.io.IOException;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;

public class CurrencyNetworkDataSource implements CurrencyDataSource {

    private CurrencyApi api;

    @Inject
    public CurrencyNetworkDataSource (CurrencyApi api) {
        this.api = api;
    }

    @Override
    public Single<GetCurrencyRateUseCase.Result> getCurrencyRate (GetCurrencyRateUseCase.Params params) {

        RateSymbolGenerator symbolGenerator = new RateSymbolGenerator(
                params.mainCurrency,
                params.pairCurrency);

        Single<RateResponse> singleResponse = api.getRate(symbolGenerator.getSymbol());
        Single<GetCurrencyRateUseCase.Result> singleResult = singleResponse
                .map(rateResponse -> new GetCurrencyRateUseCase.Result(
                        new RateModel(params.mainCurrency,
                                params.pairCurrency,
                                Float.parseFloat(rateResponse.price))));

        return handleErrorIfPresent(singleResult);
    }

    /**
     * Intercept a Single error to handle specific error
     *
     * @param single
     * @return
     */
    public <T> Single<T> handleErrorIfPresent (Single<T> single) {
        if (single == null) {
            return null;
        }

        return single.onErrorResumeNext((Function<Throwable, SingleSource<T>>)
                throwable -> {
                    throwable.printStackTrace();
                    if (throwable instanceof IOException) {
                        return Single.error(new NoConnectionException());
                    } else {
                        return Single.error(throwable);
                    }
                });
    }

}
