package com.fab.currencycalculator.domain.models;

import com.fab.currencycalculator.data.CurrencyApi;

public class Bitcoin implements Currency{

    @Override
    public String getName () {
        return "Bitcoin";
    }

    @Override
    public String getCode () {
        return "BTC";
    }
}
