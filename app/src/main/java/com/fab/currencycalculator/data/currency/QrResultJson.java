package com.fab.currencycalculator.data.currency;

import com.fab.currencycalculator.domain.models.Currency;
import com.fab.currencycalculator.domain.models.CurrencyFactory;
import com.fab.currencycalculator.domain.models.RateModel;

import java.util.ArrayList;
import java.util.List;

public class QrResultJson {

    public float value;
    public String currencyCode;
    public String rate;
    public List<Rate> rateList;

    class Rate{
        float rate;
        String currencyCode;

        public Rate (float rate, String currencyCode) {
            this.rate = rate;
            this.currencyCode = currencyCode;
        }
    }

    public float getValue () {
        return value;
    }

    public RateModel getRate () {
        return new RateModel(CurrencyFactory.getCurrencyFromCode(currencyCode),value);
    }

    public List<RateModel> getRateList () {
        List<RateModel> list = new ArrayList<>();

        for(Rate rate : rateList) {
            list.add(new RateModel(CurrencyFactory
                    .getCurrencyFromCode(rate.currencyCode),rate.rate));
        }
        return list;
    }


    public void createJson (float value, RateModel currentRate, List<RateModel> rateList) {
        this.value = value;
        this.currencyCode = currentRate.getCurrency().getCode();
        this.rate = String.valueOf(currentRate.getRateInUsd());

        this.rateList = new ArrayList<>();
        for(RateModel rateModel : rateList) {
            this.rateList.add(new Rate(rateModel.getRateInUsd(),
                    rateModel.getCurrency().getCode()));
        }
    }



}
