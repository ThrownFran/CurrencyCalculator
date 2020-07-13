package com.fab.currencycalculator.ui.qr_reader;

import com.fab.currencycalculator.domain.models.Currency;
import com.fab.currencycalculator.domain.models.RateModel;

import java.util.List;

public class QrResultEvent {

    public float result;
    public RateModel currentRate;
    public List<RateModel> rateList;

    public QrResultEvent (float result, RateModel currentRate, List<RateModel> rateList) {
        this.result = result;
        this.currentRate = currentRate;
        this.rateList = rateList;
    }


}
