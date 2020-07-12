package com.fab.currencycalculator.ui.home;

import com.fab.currencycalculator.domain.models.Currency;
import com.fab.currencycalculator.domain.models.RateModel;
import com.fab.currencycalculator.domain.use_cases.GetCurrencyRateUseCase;
import com.fab.currencycalculator.ui.base.BasePresenter;
import com.fab.currencycalculator.ui.base.ErrorMessageFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    private GetCurrencyRateUseCase getCurrencyRateUseCase;

    private float currentValue;
    private Currency currentCurrency;
    private List<RateModel> rateList = new ArrayList<>();
    private List<Currency> currencyList;

    @Inject
    public HomePresenter (HomeContract.View view,
                          GetCurrencyRateUseCase getCurrencyRateUseCase,
                          List<Currency> currencyList,
                          ErrorMessageFactory errorMessageFactory) {
        super(view,errorMessageFactory);
        this.getCurrencyRateUseCase = getCurrencyRateUseCase;
        this.currencyList = currencyList;
    }

    @Override
    public void onCreate () {
        super.onCreate();
        view.setupViewComponents(currencyList);
    }

    @Override
    public void onDestroy () {
        super.onDestroy();
    }

    @Override
    public List<RateModel> getRateList () {
        return rateList;
    }

    //region Click events

    @Override
    public void clickCalculate (String value) {

        ValidateValue validator = new ValidateValue(value);

        if(!validator.isValid){
            return;
        }

        this.currentValue = validator.value;
        this.rateList.clear();
        handleCalculateAllCurrencies();
    }

    @Override
    public void onSelectCurrency (Currency currency) {
        this.currentCurrency = currency;
    }

    //endregion Click events

    @Override
    public float getCurrentValue () {
        return currentValue;
    }

    private void handleCalculateAllCurrencies () {

        view.showProgress();

        for(Currency pairCurrency : currencyList){

            if(currentCurrency == pairCurrency){
                continue;
            }

            addDisposible(getCurrencyRateUseCase.execute(new GetCurrencyRateUseCase
                    .Params(currentCurrency,pairCurrency))
                    .doOnSubscribe( disposable -> view.showProgress())
                    .doFinally(()-> view.hideProgress())
                    .subscribe(this :: onSuccessToGetCurrencyRate, this :: onError));
        }

    }

    private void onError (Throwable throwable) {
        view.hideProgress();
        view.showErrorMessage(getErrorMessage(throwable));
    }

    private void onSuccessToGetCurrencyRate (GetCurrencyRateUseCase.Result result) {
        rateList.add(result.rateModel);
        view.notifyListUpdate();
    }

    //region Helper class

    class ValidateValue {
        public float value;
        public boolean isValid = true;

        public ValidateValue (String valueAsString) {
            validate(valueAsString);
        }

        private void validate (String valueAsString) {
            if(valueAsString == null){
                isValid = false;
            }else if(valueAsString.isEmpty()){
                isValid = false;
            }

            try {
                value = Float.parseFloat(valueAsString);
                isValid = true;
            }catch (Exception e){
                isValid = false;
            }
        }
    }

    //endregion Helper class
}
