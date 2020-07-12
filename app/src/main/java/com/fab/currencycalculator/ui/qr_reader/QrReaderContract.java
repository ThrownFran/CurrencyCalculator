package com.fab.currencycalculator.ui.qr_reader;

import com.fab.currencycalculator.domain.models.Currency;
import com.fab.currencycalculator.domain.models.RateModel;

import java.util.List;

public interface QrReaderContract {

    interface Presenter {
        void onCreate ();
        void onDestroy ();
        void onScanResult (String text);
    }

    interface View {
        void showPermissionView (QrReaderPresenter.PermissionListener listener);
        void showScanner ();
        void showMessagePermissionRequired ();
        void navigateToHome ();
        void stopScanner ();
    }
}
