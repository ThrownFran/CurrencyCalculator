package com.fab.currencycalculator.data.currency;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CurrencyApi {

    @GET("ticker/price")
    Single<RateResponse> getRate (@Query("symbol") String symbol);


}
