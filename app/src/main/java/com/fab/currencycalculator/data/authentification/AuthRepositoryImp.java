package com.fab.currencycalculator.data.authentification;

import com.fab.currencycalculator.domain.repositories.AuthRepository;
import com.fab.currencycalculator.domain.use_cases.LoginUseCase;
import com.fab.currencycalculator.domain.use_cases.LogoutUseCase;

import javax.inject.Inject;

import io.reactivex.Single;

public class AuthRepositoryImp implements AuthRepository {

    private AuthDataSource dataSource;

    @Inject
    public AuthRepositoryImp (AuthDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Single<LoginUseCase.Result> login (LoginUseCase.Params params) {
        return dataSource.login(params);
    }

    @Override
    public Single<LogoutUseCase.Result> logout (LogoutUseCase.Params params) {
        return dataSource.logout(params);
    }
}
