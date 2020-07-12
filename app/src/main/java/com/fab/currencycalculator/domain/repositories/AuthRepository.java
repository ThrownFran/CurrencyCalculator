package com.fab.currencycalculator.domain.repositories;

import com.fab.currencycalculator.domain.use_cases.LoginUseCase;
import com.fab.currencycalculator.domain.use_cases.LogoutUseCase;

import io.reactivex.Single;

public interface AuthRepository {
    Single<LoginUseCase.Result> login (LoginUseCase.Params params);
    Single<LogoutUseCase.Result> logout (LogoutUseCase.Params params);
}
