package com.fab.currencycalculator.domain.models;

public class Ethereum implements Currency {

    @Override
    public String getName () {
        return "Ethereum";
    }

    @Override
    public String getCode () {
        return "ETH";
    }
}



