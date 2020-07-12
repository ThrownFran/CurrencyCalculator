package com.fab.currencycalculator.domain.models;

public class RateModel {

    private Currency mainCurrency;
    private Currency pairCurrency;
    private float rate;

    public RateModel (Currency mainCurrency,
                      Currency pairCurrency,
                      float rate) {
        this.mainCurrency = mainCurrency;
        this.pairCurrency = pairCurrency;
        this.rate = rate;
    }

    public float getRate () {
        return rate;
    }

    public Currency getMainCurrency () {
        return mainCurrency;
    }

    public Currency getPairCurrency () {
        return pairCurrency;
    }
}
