package com.fab.currencycalculator.ui.base;

import android.content.res.Resources;

import com.fab.currencycalculator.R;
import com.fab.currencycalculator.data.exceptions.LogoutFailedException;
import com.fab.currencycalculator.data.exceptions.NoConnectionException;
import com.google.gson.JsonParseException;

import java.text.ParseException;

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
        }else if(e instanceof LogoutFailedException){
            message = resources.getString(R.string.logout_failed);
        }else if(e instanceof JsonParseException){
            message = resources.getString(R.string.qr_not_valid);
        }

        return message;
    }

}
