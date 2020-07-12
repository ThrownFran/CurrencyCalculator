package com.fab.currencycalculator.domain.models;

public class Euro implements Currency {

    @Override
    public String getName () {
        return "EURO";
    }

    @Override
    public String getCode () {
        return "EUR";
    }
}
