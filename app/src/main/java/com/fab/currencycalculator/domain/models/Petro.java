package com.fab.currencycalculator.domain.models;

public class Petro implements Currency {

    @Override
    public String getName () {
        return "Petro";
    }

    @Override
    public String getCode () {
        return "PTR";
    }
}
