package com.fab.currencycalculator.ui.qr_reader;

import com.fab.currencycalculator.data.currency.QrResultJson;
import com.fab.currencycalculator.domain.CurrencyParser;
import com.fab.currencycalculator.domain.models.Currency;
import com.fab.currencycalculator.domain.models.RateModel;
import com.fab.currencycalculator.ui.RxBus;
import com.fab.currencycalculator.ui.base.BasePresenter;
import com.fab.currencycalculator.ui.base.ErrorMessageFactory;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;

import java.text.ParseException;
import java.util.List;

import javax.inject.Inject;

public class QrReaderPresenter extends BasePresenter<QrReaderContract.View>
        implements QrReaderContract.Presenter {

    private RxBus bus;
    private CurrencyParser currencyParser;

    @Inject
    public QrReaderPresenter (QrReaderContract.View view,
                              RxBus rxBus,
                              CurrencyParser currencyParser,
                              ErrorMessageFactory errorMessageFactory) {
        super(view,errorMessageFactory);
        this.bus = rxBus;
        this.currencyParser = currencyParser;
    }

    @Override
    public void onCreate () {
        super.onCreate();
        showPermissionView();
    }

    @Override
    public void onDestroy () {
        view.stopScanner();
        super.onDestroy();
    }

    private void showPermissionView () {
        view.showPermissionView(new PermissionListener() {
            @Override
            public void onGranted () {
                view.showScanner();
            }

            @Override
            public void onRevoked () {
                view.showMessagePermissionRequired();
                view.navigateToHome();
            }
        });
    }

    @Override
    public void onScanResult (String text) {

        //Parse results
        try {
            float value = currencyParser.getValue(text);
            RateModel rateModel = currencyParser.getRate(text);
            List<RateModel> rateList = currencyParser.getRates(text);

            //Send to BUS
            bus.send(new QrResultEvent(value,rateModel,rateList));
            view.navigateToHome();

        }catch (JsonParseException e){
            view.showErrorMessage(getErrorMessage(e));
            view.navigateToHome();
        }
    }

    interface PermissionListener {
        void onGranted ();
        void onRevoked ();
    }
}
