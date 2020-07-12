package com.fab.currencycalculator.ui.auth;

import com.fab.currencycalculator.domain.use_cases.CheckValidTokenUseCase;
import com.fab.currencycalculator.domain.use_cases.LoginUseCase;
import com.fab.currencycalculator.ui.base.BasePresenter;
import com.fab.currencycalculator.ui.base.ErrorMessageFactory;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    private CheckValidTokenUseCase checkValidTokenUseCase;
    private LoginUseCase loginUseCase;

    @Inject
    public LoginPresenter (LoginContract.View view,
                           ErrorMessageFactory errorMessageFactory,
                           LoginUseCase loginUseCase,
                            CheckValidTokenUseCase checkValidTokenUseCase) {
        super(view, errorMessageFactory);
        this.loginUseCase = loginUseCase;
        this.checkValidTokenUseCase = checkValidTokenUseCase;
    }

    @Override
    public void onCreate () {
        super.onCreate();
        checkToken();
    }

    @Override
    public void onDestroy () {
        super.onDestroy();
    }

    //region Click events

    @Override
    public void clickLogin (String username, String password) {
        if(isUserEmpty(username)){
            view.showUsernameEmptyMessage();
            return;
        }else if(isPasswordEmpty(password)){
            view.showPasswordEmptyMessage();
            return;
        }else if(!isPasswordLengthValid(password)){
            view.showPasswordInvalidLength();
            return;
        }

        handleLogin(username,password);
    }

    //endregion Click events

    private void handleLogin (String username, String password) {
        addDisposible(loginUseCase.execute(new LoginUseCase.Params(username,password))
        .subscribe(this :: onSuccessToLogin, this :: onErrorToLogin));
    }

    private void onErrorToLogin (Throwable throwable) {
        view.showGenericMessage(getErrorMessage(throwable));
    }

    private void onSuccessToLogin (LoginUseCase.Result result) {
        view.navigateToHome();
    }

    private void checkToken () {
        addDisposible(checkValidTokenUseCase.execute(new CheckValidTokenUseCase.Params())
                .subscribe(this :: onSuccessToCheckToken,
                        this :: onErrorToCheckToken));
    }

    private void onSuccessToCheckToken (CheckValidTokenUseCase.Result result) {
        if(result.hasValidToken){
            view.navigateToHome();
        }
    }

    private void onErrorToCheckToken (Throwable throwable) {
        view.showGenericMessage(getErrorMessage(throwable));
    }

    //region Helper methods

    private boolean isUserEmpty (String username) {
        return username == null || username.isEmpty();
    }

    private boolean isPasswordLengthValid (String password) {
        return password != null && password.trim().length() > 5;
    }

    private boolean isPasswordEmpty (String password) {
        return password == null || password.isEmpty();
    }

    //endregion Helper methods

}
