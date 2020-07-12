package com.fab.currencycalculator.ui.home.di;


import com.fab.currencycalculator.dependency_injection.PresentationScope;
import com.fab.currencycalculator.domain.models.BS;
import com.fab.currencycalculator.domain.models.Bitcoin;
import com.fab.currencycalculator.domain.models.Currency;
import com.fab.currencycalculator.domain.models.Ethereum;
import com.fab.currencycalculator.domain.models.Euro;
import com.fab.currencycalculator.domain.models.Petro;
import com.fab.currencycalculator.domain.models.Usd;
import com.fab.currencycalculator.ui.home.HomeContract;
import com.fab.currencycalculator.ui.home.HomeFragment;
import com.fab.currencycalculator.ui.home.HomePresenter;
import com.fab.currencycalculator.ui.home.RateAdapter;

import java.util.ArrayList;
import java.util.List;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class HomeModule {

    @PresentationScope
    @Provides
    static List<Currency> getCurrencies () {
        List<Currency> currencyList = new ArrayList<>();
        currencyList.add(new Euro());
        currencyList.add(new Ethereum());
        currencyList.add(new Bitcoin());
        currencyList.add(new BS());
        currencyList.add(new Petro());
        return currencyList;
    }

    @PresentationScope
    @Binds
    abstract HomeContract.Presenter presenter (HomePresenter presenter);

    @PresentationScope
    @Binds
    abstract HomeContract.View view (HomeFragment viewImpl);

    @PresentationScope
    @Provides
    static RateAdapter adapter (HomeContract.Presenter presenter) {
        return new RateAdapter(presenter);
    }

}
