package com.fab.currencycalculator.ui.home;

import com.fab.currencycalculator.domain.models.Currency;
import com.fab.currencycalculator.domain.models.RateModel;

import java.util.List;

public interface HomeContract {

    interface Presenter {
        void clickCalculate (String value);
        List<RateModel> getRateList ();
        float getCurrentValue ();
        void onCreate ();
        void onDestroy ();
        void onSelectCurrency (Currency currency);
        RateModel getCurrentRate ();
    }

    interface View {
        void showProgress ();
        void hideProgress ();
        void setupViewComponents (List<Currency> currencyList);
        void notifyListUpdate ();
        void showErrorMessage (String errorMessage);
        void generateQr (String hello);
    }
}
