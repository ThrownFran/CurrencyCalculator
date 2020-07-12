package com.fab.currencycalculator.ui;

import android.app.Activity;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;

public abstract class Utils {

    public static void hideSoftKeyboard (Activity activity) {

        if (activity == null) {
            return;
        }

        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(
                Activity.INPUT_METHOD_SERVICE);

        if (activity.getCurrentFocus() != null && activity
                .getCurrentFocus()
                .getWindowToken() != null) {
            inputMethodManager.hideSoftInputFromWindow(activity
                    .getCurrentFocus()
                    .getWindowToken(), 0);
        }
    }

}
