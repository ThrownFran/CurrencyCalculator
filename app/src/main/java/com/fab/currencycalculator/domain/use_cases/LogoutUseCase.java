package com.fab.currencycalculator.domain.use_cases;

import com.fab.currencycalculator.domain.repositories.AuthRepository;
import com.fab.currencycalculator.domain.schedulers.SchedulersFacade;

import javax.inject.Inject;

import io.reactivex.Single;

public class LogoutUseCase extends BaseUseCase<LogoutUseCase.Result, LogoutUseCase.Params> {

    private AuthRepository repository;

    @Inject
    public LogoutUseCase (SchedulersFacade schedulers,
                          AuthRepository repository) {
        super(schedulers);
        this.repository = repository;
    }

    @Override
    protected Single<Result> buildUseCaseObservable (Params params) {
        return repository.logout(params);
    }

    public static class Params {}

    public static class Result {
        public Result () {
        }
    }
}
