package com.fab.currencycalculator.data.currency;

import com.fab.currencycalculator.domain.CurrencyParser;
import com.fab.currencycalculator.domain.models.RateModel;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.util.List;

public class CurrencyParserImp implements CurrencyParser {

    @Override
    public float getValue (String text) throws JsonParseException {
        QrResultJson bundle = new Gson().fromJson(text, QrResultJson.class);
        return bundle.getValue();
    }

    @Override
    public RateModel getRate (String text) throws JsonParseException {
        QrResultJson bundle = new Gson().fromJson(text, QrResultJson.class);
        return bundle.getRate();
    }

    @Override
    public List<RateModel> getRates (String text) throws JsonParseException {
        QrResultJson bundle = new Gson().fromJson(text, QrResultJson.class);
        return bundle.getRateList();
    }

    @Override
    public String createJson (float value, RateModel rateModel,
                              List<RateModel> rateModels) throws JsonParseException {
        QrResultJson bundle = new QrResultJson();
        bundle.createJson(value, rateModel, rateModels);
        Gson gson = new Gson();
        return gson.toJson(bundle);
    }
}
