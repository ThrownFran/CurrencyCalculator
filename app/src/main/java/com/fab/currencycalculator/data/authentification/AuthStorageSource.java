package com.fab.currencycalculator.data.authentification;

import com.fab.currencycalculator.data.exceptions.LogoutFailedException;
import com.fab.currencycalculator.domain.use_cases.LoginUseCase;
import com.fab.currencycalculator.domain.use_cases.LogoutUseCase;

import javax.inject.Inject;

import io.reactivex.Single;

public class AuthStorageSource implements AuthDataSource {

    private SessionManagerImp storageManager;

    @Inject
    public AuthStorageSource (SessionManagerImp storageManager) {
        this.storageManager = storageManager;
    }

    @Override
    public Single<LoginUseCase.Result> login (LoginUseCase.Params params) {

        //Generate Token
        String token = "PQ83hfa0aBnsl18JUaedoi21Kjs";

        //Save in storage
        storageManager.setToken(token);
        storageManager.setUsername(params.username);

        return Single.just(new LoginUseCase.Result("Login success"));
    }

    @Override
    public Single<LogoutUseCase.Result> logout (LogoutUseCase.Params params) {

        if(storageManager.clear()){
            return Single.just(new LogoutUseCase.Result());
        }else{
            return Single.error(new LogoutFailedException());
        }
    }
}
