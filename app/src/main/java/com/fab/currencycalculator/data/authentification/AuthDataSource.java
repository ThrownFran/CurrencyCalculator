package com.fab.currencycalculator.data.authentification;

import com.fab.currencycalculator.domain.use_cases.LoginUseCase;
import com.fab.currencycalculator.domain.use_cases.LogoutUseCase;

import io.reactivex.Single;

public interface AuthDataSource {
    Single<LoginUseCase.Result> login (LoginUseCase.Params params);
    Single<LogoutUseCase.Result> logout (LogoutUseCase.Params params);
}
