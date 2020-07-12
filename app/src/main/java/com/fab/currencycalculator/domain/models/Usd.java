package com.fab.currencycalculator.domain.models;

public class Usd implements Currency {

    @Override
    public String getName () {
        return "US dollars";
    }

    @Override
    public String getCode () {
        return "USDT";
    }
}
