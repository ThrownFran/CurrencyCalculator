package com.fab.currencycalculator.data.currency.sources;

import com.fab.currencycalculator.domain.models.BS;
import com.fab.currencycalculator.domain.models.Currency;
import com.fab.currencycalculator.domain.models.Petro;
import com.fab.currencycalculator.domain.models.RateModel;
import com.fab.currencycalculator.domain.use_cases.GetCurrencyRateUseCase;

import io.reactivex.Single;

public class CurrencyStaticDataSource implements CurrencyDataSource {

    @Override
    public Single<GetCurrencyRateUseCase.Result> getCurrencyRate (GetCurrencyRateUseCase.Params params) {
        return Single.just(new GetCurrencyRateUseCase.Result(getRate(params.currency)));
    }

    private RateModel getRate (Currency currency) {
        if(currency instanceof Petro){
            return new RateModel(currency,58.97F);
        }else if(currency instanceof BS){
            return new RateModel(currency,0.000005F);
        }else{
            throw new IllegalArgumentException("Not supported currency "+currency.toString());
        }
    }
}
