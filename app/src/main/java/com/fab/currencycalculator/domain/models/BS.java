package com.fab.currencycalculator.domain.models;

public class BS implements Currency {

    @Override
    public String getName () {
        return "Bolivar";
    }

    @Override
    public String getCode () {
        return "BS";
    }
}
