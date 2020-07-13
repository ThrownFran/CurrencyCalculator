package com.fab.currencycalculator.ui.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<ContractView> {

    protected final ContractView view;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private ErrorMessageFactory errorMessageFactory;

    public BasePresenter (ContractView view,
                          ErrorMessageFactory errorMessageFactory) {
        this.view = view;
        this.errorMessageFactory = errorMessageFactory;
    }

    public String getErrorMessage(Throwable e) {
        return errorMessageFactory.getMessage(e);
    }

    public void onCreate () {
    }

    public void onDestroy () {
        compositeDisposable.clear();
    }

    public void addDisposible (Disposable disposable) {
        compositeDisposable.add(disposable);
    }
}
