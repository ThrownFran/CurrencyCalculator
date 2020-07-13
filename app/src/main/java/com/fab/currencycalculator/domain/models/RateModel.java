package com.fab.currencycalculator.domain.models;

public class RateModel {

    private Currency currency;
    private float rateInUsd;

    public RateModel (Currency currency,
                      float rate) {
        this.currency = currency;
        this.rateInUsd = rate;
    }

    public Currency getCurrency () {
        return currency;
    }

    public float getRateInUsd () {
        return rateInUsd;
    }

    public Result getValueInThisCurrency (RateModel rateOfOtherCurrency) {

        if(rateOfOtherCurrency.currency.getCode().equals(currency.getCode())){
            return new Result(rateInUsd,new Usd());
        }

        return new Result(rateOfOtherCurrency.rateInUsd/rateInUsd, currency);
    }

    public static class Result {
        public float value;
        public Currency currency;

        public Result (float value, Currency currency) {
            this.value = value;
            this.currency = currency;
        }
    }


}
