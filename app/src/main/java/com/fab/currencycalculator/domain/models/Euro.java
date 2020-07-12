package com.fab.currencycalculator.domain.models;

import com.fab.currencycalculator.data.CurrencyApi;

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
