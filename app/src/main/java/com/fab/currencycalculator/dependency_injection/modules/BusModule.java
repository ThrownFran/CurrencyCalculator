package com.fab.currencycalculator.dependency_injection.modules;


import com.fab.currencycalculator.ui.RxBus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BusModule {

    @Singleton
    @Provides
    RxBus bus () {
        return new RxBus();
    }

}
