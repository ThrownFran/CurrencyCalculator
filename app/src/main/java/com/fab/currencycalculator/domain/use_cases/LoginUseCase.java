package com.fab.currencycalculator.domain.use_cases;

import com.fab.currencycalculator.domain.repositories.AuthRepository;
import com.fab.currencycalculator.domain.SchedulersFacade;

import javax.inject.Inject;

import io.reactivex.Single;

public class LoginUseCase extends BaseUseCase<LoginUseCase.Result, LoginUseCase.Params> {

    private AuthRepository repository;

    @Inject
    public LoginUseCase (SchedulersFacade schedulers,
                         AuthRepository repository) {
        super(schedulers);
        this.repository = repository;
    }

    @Override
    protected Single<Result> buildUseCaseObservable (Params params) {
        return repository.login(params);
    }

    public static class Params {
        public String username;
        public String password;

        public Params (String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

    public static class Result {
        public String message;

        public Result (String message) {
            this.message = message;
        }
    }
}
