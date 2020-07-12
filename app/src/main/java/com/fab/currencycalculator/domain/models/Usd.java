package com.fab.currencycalculator.domain.models;

public class Usd implements Currency {

    @Override
    public String getName () {
        return "US dollar";
    }

    @Override
    public String getCode () {
        return "USDT";
    }
}
