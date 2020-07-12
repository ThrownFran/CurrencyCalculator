package com.fab.currencycalculator.ui;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.fab.currencycalculator.ui.home.HomeFragment;

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

    public static void showToast (Context context, String message) {
        Toast.makeText(context,message,Toast.LENGTH_LONG).show();
    }
}
