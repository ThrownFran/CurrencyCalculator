package com.fab.currencycalculator.ui.main;

import com.fab.currencycalculator.domain.SessionManager;
import com.fab.currencycalculator.domain.use_cases.CheckValidTokenUseCase;
import com.fab.currencycalculator.domain.use_cases.LoginUseCase;
import com.fab.currencycalculator.domain.use_cases.LogoutUseCase;
import com.fab.currencycalculator.ui.base.BasePresenter;
import com.fab.currencycalculator.ui.base.ErrorMessageFactory;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private LogoutUseCase logoutUseCase;
    private CheckValidTokenUseCase checkValidTokenUseCase;
    private SessionManager sessionManager;

    @Inject
    public MainPresenter (MainContract.View view,
                          LogoutUseCase logoutUseCase,
                          CheckValidTokenUseCase checkValidTokenUseCase,
                          SessionManager sessionManager,
                          ErrorMessageFactory errorMessageFactory) {
        super(view, errorMessageFactory);
        this.checkValidTokenUseCase = checkValidTokenUseCase;
        this.logoutUseCase = logoutUseCase;
        this.sessionManager = sessionManager;
    }

    @Override
    public void onCreate () {
        super.onCreate();
        checkToken();
        setupUsername();
    }

    @Override
    public void onDestroy () {
        super.onDestroy();
    }

    private void setupUsername () {
        view.showUsername(sessionManager.getUsername());
    }

    private void checkToken () {
        addDisposible(checkValidTokenUseCase.execute(new CheckValidTokenUseCase.Params())
                .subscribe(this :: onSuccessToCheckToken,
                        this :: onErrorToCheckToken));
    }

    private void onSuccessToCheckToken (CheckValidTokenUseCase.Result result) {
        if(!result.hasValidToken){
            handleLogout();
        }
    }

    private void onErrorToCheckToken (Throwable throwable) {
        view.showGenericMessage(getErrorMessage(throwable));
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
