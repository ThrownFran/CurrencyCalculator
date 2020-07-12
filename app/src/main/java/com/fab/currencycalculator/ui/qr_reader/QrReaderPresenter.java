package com.fab.currencycalculator.ui.qr_reader;

import com.fab.currencycalculator.domain.models.Currency;
import com.fab.currencycalculator.domain.models.RateModel;
import com.fab.currencycalculator.domain.models.Usd;
import com.fab.currencycalculator.domain.use_cases.GetCurrencyRateUseCase;
import com.fab.currencycalculator.ui.RxBus;
import com.fab.currencycalculator.ui.base.BasePresenter;
import com.fab.currencycalculator.ui.base.ErrorMessageFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class QrReaderPresenter extends BasePresenter<QrReaderContract.View>
        implements QrReaderContract.Presenter {

    private RxBus bus;

    @Inject
    public QrReaderPresenter (QrReaderContract.View view,
                              RxBus rxBus,
                              ErrorMessageFactory errorMessageFactory) {
        super(view,errorMessageFactory);
        this.bus = rxBus;
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
        //Send to BUS
        bus.send(new QrResultEvent(text));
        view.navigateToHome();
    }

    interface PermissionListener {
        void onGranted ();
        void onRevoked ();
    }
}
