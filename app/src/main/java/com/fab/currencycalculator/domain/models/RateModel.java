package com.fab.currencycalculator.domain.models;

public class RateModel {

    private Currency mainCurrency;
    private float rateInUsd;

    public RateModel (Currency mainCurrency,
                      float rate) {
        this.mainCurrency = mainCurrency;
        this.rateInUsd = rate;
    }

    public float getRateInUsd () {
        return rateInUsd;
    }

    public Currency getCurrency () {
        return mainCurrency;
    }

    public Result getValueInThisCurrency (RateModel rateOfOtherCurrency) {

        if(rateOfOtherCurrency.mainCurrency.getCode().equals(mainCurrency.getCode())){
            return new Result(rateInUsd,new Usd());
        }

        return new Result(rateOfOtherCurrency.rateInUsd/rateInUsd, mainCurrency);

//        return rateOfOtherCurrency.rateInUsd/rateInUsd;
    }

//    public String getValueInThisCurrencyInString (RateModel rateOfOtherCurrency) {
//
//        if(rateOfOtherCurrency.mainCurrency.getCode().equals(mainCurrency.getCode())){
//            return getValueInThisCurrency(rateOfOtherCurrency) + " " + new Usd().getName();
//        }
//
//        return String.valueOf(getValueInThisCurrency(rateOfOtherCurrency));
//    }

    public static class Result {
        public float value;
        public Currency currency;

        public Result (float value, Currency currency) {
            this.value = value;
            this.currency = currency;
        }
    }


}
