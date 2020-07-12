package com.fab.currencycalculator.ui.qr_reader;

import android.Manifest;

import com.fab.currencycalculator.R;
import com.fab.currencycalculator.ui.Utils;
import com.fab.currencycalculator.ui.base.BaseFragment;
import com.google.zxing.Result;
import com.tbruyelle.rxpermissions2.RxPermissions;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrReaderFragment extends BaseFragment implements ZXingScannerView.ResultHandler {

    @BindView(R.id.qr_reader_scanner)
    ZXingScannerView mScannerView;

    private RxPermissions rxPermissions;

    @Override
    public int getLayout () {
        return R.layout.fragment_qr_reader;
    }

    @Override
    protected void injectDependencies () {
    }

    @Override
    protected void onCreateFragment () {

    }

    private void checkCameraPermissions () {
        rxPermissions = new RxPermissions(this);
        Disposable disposable = rxPermissions
                .request(Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) {
                        handleCamera();
                    } else {

                    }
                });
    }

    private void handleCamera () {
        // Register ourselves as a handler for scan results.
        mScannerView.setResultHandler(this);
        // Start camera on resume
        mScannerView.startCamera();
    }

    @Override
    public void onResume() {
        super.onResume();
        checkCameraPermissions();
    }

    @Override
    public void onPause() {
        super.onPause();
        // Stop camera on pause
        mScannerView.stopCamera();
    }

    @Override
    public void handleResult (Result result) {
        Utils.showToast(getContext(),result.getText());
    }
}