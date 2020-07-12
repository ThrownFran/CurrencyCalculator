package com.fab.currencycalculator.domain.use_cases;

import com.fab.currencycalculator.domain.models.Currency;
import com.fab.currencycalculator.domain.models.RateModel;
import com.fab.currencycalculator.domain.repositories.CurrencyRepository;
import com.fab.currencycalculator.domain.schedulers.SchedulersFacade;

import javax.inject.Inject;

import io.reactivex.Single;

public class GetCurrencyRateUseCase extends BaseUseCase<GetCurrencyRateUseCase.Result, GetCurrencyRateUseCase.Params> {

    private CurrencyRepository repository;

    @Inject
    public GetCurrencyRateUseCase (SchedulersFacade schedulers,
                                   CurrencyRepository repository) {
        super(schedulers);
        this.repository = repository;
    }

    @Override
    protected Single<Result> buildUseCaseObservable (Params params) {
        return repository.getCurrencyRate(params);
    }

    public static class Params {
        public Currency mainCurrency;
        public Currency pairCurrency;

        public Params (Currency mainCurrency,
                       Currency pairCurrency) {
            this.mainCurrency = mainCurrency;
            this.pairCurrency = pairCurrency;
        }
    }

    public static class Result {
        public RateModel rateModel;

        public Result (RateModel rateModel) {
            this.rateModel = rateModel;
        }
    }
}
