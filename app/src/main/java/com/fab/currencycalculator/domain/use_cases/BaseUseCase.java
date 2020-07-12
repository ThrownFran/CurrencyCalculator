package com.fab.currencycalculator.domain.use_cases;

import com.fab.currencycalculator.domain.schedulers.SchedulersFacade;

import io.reactivex.Single;

public abstract class BaseUseCase <Result, Params> {

    protected SchedulersFacade schedulers;

    public BaseUseCase (SchedulersFacade schedulers) {
        this.schedulers = schedulers;
    }

    protected abstract Single<Result> buildUseCaseObservable (Params params);

    public Single<Result> execute (Params params) {
        return buildUseCaseObservable(params)
                .subscribeOn(schedulers.io())
                .observeOn(schedulers.ui());
    }

}
