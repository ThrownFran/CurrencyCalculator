package com.fab.currencycalculator.ui.home;

import com.fab.currencycalculator.domain.CurrencyParser;
import com.fab.currencycalculator.domain.models.Currency;
import com.fab.currencycalculator.domain.models.RateModel;
import com.fab.currencycalculator.domain.models.Usd;
import com.fab.currencycalculator.domain.use_cases.GetCurrencyRateUseCase;
import com.fab.currencycalculator.ui.RxBus;
import com.fab.currencycalculator.ui.base.BasePresenter;
import com.fab.currencycalculator.ui.base.ErrorMessageFactory;
import com.fab.currencycalculator.ui.qr_reader.QrResultEvent;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;

public class HomePresenter extends BasePresenter<HomeContract.View> implements HomeContract.Presenter {

    private GetCurrencyRateUseCase getCurrencyRateUseCase;
    private RxBus bus;
    private List<Currency> currencyList;
    private CurrencyParser currencyParser;

    private float currentValue;
    private Currency currentCurrency;
    private RateModel currentRate;
    private List<RateModel> rateList = new ArrayList<>();
    private int requestsStarted = 0;
    private int requestsFinished = 0;

    @Inject
    public HomePresenter (HomeContract.View view,
                          GetCurrencyRateUseCase getCurrencyRateUseCase,
                          List<Currency> currencyList,
                          RxBus bus,
                          CurrencyParser currencyParser,
                          ErrorMessageFactory errorMessageFactory) {
        super(view,errorMessageFactory);
        this.getCurrencyRateUseCase = getCurrencyRateUseCase;
        this.currencyList = currencyList;
        this.bus = bus;
        this.currencyParser = currencyParser;
    }

    @Override
    public void onCreate () {
        super.onCreate();
        view.setupViewComponents(currencyList);
        setDefaultCurrency();
        setupBus();
    }

    //region Events

    private void setupBus () {
        addDisposible(bus.toPublishObservable()
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object -> {
                    if (object instanceof QrResultEvent) {
                        onEventQrResult((QrResultEvent)object);
                    }
                }));
    }

    private void onEventQrResult (QrResultEvent event) {
        bus.removeSticky();

        if(getCurrencyInList(event.currentRate.getCurrency()) == null){
            view.showCurrencyNotSupportedMessage();
            return;
        }

        showQrResult(event);
    }

    private void showQrResult (QrResultEvent event) {
        this.currentValue = event.result;
        this.currentRate = event.currentRate;

        view.setValue(event.result);
        view.setCurrency(currencyList.indexOf(getCurrencyInList(event.currentRate.getCurrency())));

        updateWithQrRateList(event.rateList);
    }

    private void updateWithQrRateList (List<RateModel> rateList) {
        this.rateList.clear();
        this.rateList.addAll(rateList);
        view.notifyListUpdate();
        view.setRecalculateButton();
    }

    //endregion Event

    private void setDefaultCurrency () {
        this.currentCurrency = currencyList.get(0);
    }

    @Override
    public void onDestroy () {
        super.onDestroy();
    }

    @Override
    public List<RateModel> getRateList () {
        return rateList;
    }

    @Override
    public RateModel getCurrentRate () {
        return currentRate;
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

        if(currentCurrency instanceof Usd){
            this.currentRate = new RateModel(currentCurrency,1);
            handleCalculateAllCurrencies();
        }else{
            handleCalculateCurrentRate();
        }
    }

    @Override
    public void onSelectCurrency (Currency currency) {
        this.currentCurrency = currency;
    }

    //endregion Click events

    private void handleCalculateCurrentRate () {
        addDisposible(getCurrencyRateUseCase.execute(new GetCurrencyRateUseCase
                .Params(currentCurrency))
                .doOnSubscribe( disposable -> view.showProgress())
                .subscribe(this :: onSuccessToGetCurrentRate, this :: onError));
    }

    private void onSuccessToGetCurrentRate (GetCurrencyRateUseCase.Result result) {
        this.currentRate = result.rateModel;
        rateList.add(this.currentRate);

        handleCalculateAllCurrencies();
    }

    @Override
    public float getCurrentValue () {
        return currentValue;
    }

    private void handleCalculateAllCurrencies () {

        requestsFinished = 0;
        requestsStarted = 0;

        for(Currency currency : currencyList){

            if(currentCurrency.getCode().equals(currency.getCode())
                    || currency instanceof Usd){
                continue;
            }

            addDisposible(getCurrencyRateUseCase.execute(new GetCurrencyRateUseCase
                    .Params(currency))
                    .doOnSubscribe( disposable -> {
                        view.showProgress();
                        requestsStarted++;
                    })
                    .doFinally(view::hideProgress)
                    .subscribe(this :: onSuccessToGetCurrencyRate, this :: onError));
        }

    }

    private void onError (Throwable throwable) {
        view.hideProgress();
        view.showErrorMessage(getErrorMessage(throwable));
        requestsFinished++;
    }

    private void onSuccessToGetCurrencyRate (GetCurrencyRateUseCase.Result result) {
        rateList.add(result.rateModel);
        view.notifyListUpdate();
        requestsFinished++;

        if(isRequestFinished()){
            generateQr();
        }
    }

    private void generateQr () {
        String input = currencyParser.createJson(currentValue,currentRate,rateList);
        view.generateQr(input);
    }

    private boolean isRequestFinished () {
        return requestsFinished == requestsStarted;
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

    private Currency getCurrencyInList (Currency currency) {
        Currency matcher = null;
        for(Currency item : currencyList){
            if(item.getCode().equals(currency.getCode())){
                matcher = item;
                break;
            }
        }

        return matcher;
    }

    //endregion Helper class
}
