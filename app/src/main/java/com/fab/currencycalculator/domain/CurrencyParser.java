package com.fab.currencycalculator.domain;

import com.fab.currencycalculator.domain.models.Currency;
import com.fab.currencycalculator.domain.models.RateModel;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;

import java.text.ParseException;
import java.util.List;

public interface CurrencyParser {
    float getValue (String json) throws JsonParseException;
    RateModel getRate (String json) throws JsonParseException;
    List<RateModel> getRates(String json) throws JsonParseException;
    String createJson (float value, RateModel rateModel, List<RateModel> rateModels);
}
