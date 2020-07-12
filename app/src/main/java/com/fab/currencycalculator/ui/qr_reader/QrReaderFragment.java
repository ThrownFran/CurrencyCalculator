package com.fab.currencycalculator.ui.qr_reader;

import android.Manifest;

import com.fab.currencycalculator.BaseApplication;
import com.fab.currencycalculator.R;
import com.fab.currencycalculator.ui.Utils;
import com.fab.currencycalculator.ui.base.BaseFragment;
import com.google.zxing.Result;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

import androidx.navigation.Navigation;
import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QrReaderFragment extends BaseFragment
        implements ZXingScannerView.ResultHandler,QrReaderContract.View {

    @BindView(R.id.qr_reader_scanner)
    ZXingScannerView mScannerView;

    @Inject
    QrReaderContract.Presenter presenter;

    private RxPermissions rxPermissions;

    @Override
    public int getLayout () {
        return R.layout.fragment_qr_reader;
    }

    @Override
    protected void injectDependencies () {
        BaseApplication.getInstance()
                .getAppComponent()
                .plusQrReader()
                .create(this)
                .inject(this);
    }

    @Override
    public void showPermissionView (QrReaderPresenter.PermissionListener listener) {
        rxPermissions = new RxPermissions(this);
        Disposable disposable = rxPermissions
                .request(Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) {
                        listener.onGranted();
                    } else {
                        listener.onRevoked();
                    }
                });
    }

    @Override
    public void showScanner () {
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();
    }

    @Override
    public void showMessagePermissionRequired () {
        Utils.showToast(getContext(),getString(R.string.permission_required_camera));
    }

    @Override
    public void navigateToHome () {
        Navigation.findNavController(getView()).popBackStack();
    }

    @Override
    public void stopScanner () {
        mScannerView.stopCamera();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onCreate();
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onDestroy();
    }

    @Override
    public void handleResult (Result result) {
        presenter.onScanResult(result.getText());
//        Utils.showToast(getContext(),result.getText());
    }
}