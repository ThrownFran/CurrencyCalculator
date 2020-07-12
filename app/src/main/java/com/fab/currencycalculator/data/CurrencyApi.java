package com.fab.currencycalculator.data;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CurrencyApi {

    @GET("ticker/price")
    Single<RateResponse> getRate (@Query("symbol") String symbol);


}
