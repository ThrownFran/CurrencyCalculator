package com.fab.currencycalculator.ui.base;

import android.content.res.Resources;

import com.fab.currencycalculator.R;
import com.fab.currencycalculator.data.NoConnectionException;

import javax.inject.Inject;

public class ErrorMessageFactory {

    private Resources resources;

    @Inject
    public ErrorMessageFactory (Resources resources) {
        this.resources = resources;
    }

    public ErrorMessageFactory () {
    }

    public String getMessage (Throwable e) {
        String message =resources.getString(R.string.all_unknown_error);

        if (e instanceof NoConnectionException) {
            message = resources.getString(R.string.all_no_connection);
        }

        return message;
    }

}
