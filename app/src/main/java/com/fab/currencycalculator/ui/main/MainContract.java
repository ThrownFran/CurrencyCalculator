package com.fab.currencycalculator.ui.main;

public interface MainContract {

    interface Presenter {
        void clickLogout ();
        void onCreate ();
        void onDestroy ();
    }

    interface View {
        void showMessage (String errorMessage);
        void navigateToLogin ();
        void showGenericMessage (String errorMessage);
        void showUsername (String username);
    }
}
