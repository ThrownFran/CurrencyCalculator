package com.fab.currencycalculator.ui.main;

import com.fab.currencycalculator.domain.use_cases.CheckValidTokenUseCase;
import com.fab.currencycalculator.domain.use_cases.LoginUseCase;
import com.fab.currencycalculator.domain.use_cases.LogoutUseCase;
import com.fab.currencycalculator.ui.base.BasePresenter;
import com.fab.currencycalculator.ui.base.ErrorMessageFactory;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private LogoutUseCase logoutUseCase;

    @Inject
    public MainPresenter (MainContract.View view,
                          LogoutUseCase logoutUseCase,
                          ErrorMessageFactory errorMessageFactory) {
        super(view, errorMessageFactory);
        this.logoutUseCase = logoutUseCase;
    }

    @Override
    public void onCreate () {
        super.onCreate();
    }

    @Override
    public void onDestroy () {
        super.onDestroy();
    }

    @Override
    public void clickLogout () {
        handleLogout();
    }

    private void handleLogout () {
        addDisposible(logoutUseCase.execute(new LogoutUseCase.Params())
        .subscribe(this :: onSuccessToLogout, this :: onErrorToLogout));
    }

    private void onErrorToLogout (Throwable throwable) {
        view.showMessage(getErrorMessage(throwable));
    }

    private void onSuccessToLogout (LogoutUseCase.Result result) {
        view.navigateToLogin();
    }
}
