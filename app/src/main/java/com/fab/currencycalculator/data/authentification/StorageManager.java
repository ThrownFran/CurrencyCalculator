package com.fab.currencycalculator.data.authentification;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

public class StorageManager {

    private static final String PREFERENCES_APP = "currencycalculator.app.preferences";
    private static final String USER_NAME = "username";
    private static final String TOKEN = "token";

    private Context context;
    private SharedPreferences.Editor appEditor;
    private SharedPreferences appPreferences;

    @Inject
    public StorageManager (Context context) {
        this.context = context;
        appPreferences = context.getSharedPreferences(PREFERENCES_APP, Context.MODE_PRIVATE);
        appEditor = appPreferences.edit();
    }

    public void setUsername (String id) {
        appEditor.putString(USER_NAME, id);
        appEditor.commit();
    }

    public String getUsername () {
        return appPreferences.getString(USER_NAME, null);
    }

    public void setToken (String token) {
        appEditor.putString(TOKEN, token);
        appEditor.commit();
    }

    public String getToken () {
        return appPreferences.getString(TOKEN, null);
    }

    public boolean clear () {
        appEditor.clear();
        return appEditor.commit();
    }
}
