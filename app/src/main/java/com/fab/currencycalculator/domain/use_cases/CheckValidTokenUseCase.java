package com.fab.currencycalculator.domain.use_cases;

import com.fab.currencycalculator.data.authentification.StorageManager;
import com.fab.currencycalculator.domain.repositories.AuthRepository;
import com.fab.currencycalculator.domain.schedulers.SchedulersFacade;

import javax.inject.Inject;

import io.reactivex.Single;

public class CheckValidTokenUseCase extends BaseUseCase<CheckValidTokenUseCase.Result,
        CheckValidTokenUseCase.Params> {

    private StorageManager storageManager;

    @Inject
    public CheckValidTokenUseCase (SchedulersFacade schedulers,
                                   StorageManager storageManager) {
        super(schedulers);
        this.storageManager = storageManager;
    }

    @Override
    protected Single<Result> buildUseCaseObservable (Params params) {

        if(storageManager.getToken() != null){
            return Single.just(new Result(true));
        }else{
            return Single.just(new Result(false));
        }
    }

    public static class Params {}

    public static class Result {
        public boolean hasValidToken;

        public Result (boolean hasValidToken) {
            this.hasValidToken = hasValidToken;
        }
    }
}
