package com.fab.currencycalculator.dependency_injection.modules;



import android.content.Context;

import com.fab.currencycalculator.data.authentification.SessionManagerImp;
import com.fab.currencycalculator.domain.SessionManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class SessionModule {

    @Singleton
    @Provides
    SessionManager session (Context context) {
        return new SessionManagerImp(context);
    }


}
