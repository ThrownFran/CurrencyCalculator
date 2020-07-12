package com.fab.currencycalculator.data;

import com.fab.currencycalculator.domain.models.Currency;
import com.fab.currencycalculator.domain.models.Usd;
import com.google.gson.annotations.SerializedName;

public class RateSymbolGenerator {

    public Currency mainCurrency;

    public RateSymbolGenerator (Currency mainCurrency) {
        this.mainCurrency = mainCurrency;
    }

    public String getSymbol () {
        return mainCurrency.getCode() + new Usd().getCode();
    }
}
