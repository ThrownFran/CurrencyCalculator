package com.fab.currencycalculator.domain.models;

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
