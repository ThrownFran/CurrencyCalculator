package com.fab.currencycalculator.data;

import com.fab.currencycalculator.domain.models.Currency;
import com.google.gson.annotations.SerializedName;

public class RateSymbolGenerator {

    public Currency mainCurrency;
    public Currency pairCurrency;

    public RateSymbolGenerator (Currency mainCurrency, Currency pairCurrency) {
        this.mainCurrency = mainCurrency;
        this.pairCurrency = pairCurrency;
    }

    public String getSymbol () {
        return mainCurrency.getCode() + pairCurrency.getCode();
    }
}
