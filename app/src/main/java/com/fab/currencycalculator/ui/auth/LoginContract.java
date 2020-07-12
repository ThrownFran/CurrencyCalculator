package com.fab.currencycalculator.ui.auth;

public interface LoginContract {

    interface Presenter {
        void clickLogin (String username, String password);
    }

    interface View {
        void showUsernameEmptyMessage ();
        void showPasswordEmptyMessage ();
        void showPasswordInvalidLength ();
        void navigateToHome ();
        void showGenericMessage (String errorMessage);
    }
}
