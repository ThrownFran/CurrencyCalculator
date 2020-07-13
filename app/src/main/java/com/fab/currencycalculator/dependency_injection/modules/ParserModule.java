package com.fab.currencycalculator.dependency_injection.modules;


import com.fab.currencycalculator.data.currency.CurrencyParserImp;
import com.fab.currencycalculator.domain.CurrencyParser;
import com.fab.currencycalculator.ui.RxBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ParserModule {

    @Singleton
    @Provides
    CurrencyParser bus () {
        return new CurrencyParserImp();
    }

}
