package com.fab.currencycalculator.ui.main;

public interface MainContract {

    interface Presenter {
        void clickLogout ();
    }

    interface View {
        void showMessage (String errorMessage);
        void navigateToLogin ();
    }
}
