package com.fab.currencycalculator.ui.qr_reader.di;


import com.fab.currencycalculator.dependency_injection.PresentationScope;
import com.fab.currencycalculator.ui.main.MainActivity;
import com.fab.currencycalculator.ui.main.MainContract;
import com.fab.currencycalculator.ui.main.MainPresenter;
import com.fab.currencycalculator.ui.qr_reader.QrReaderContract;
import com.fab.currencycalculator.ui.qr_reader.QrReaderFragment;
import com.fab.currencycalculator.ui.qr_reader.QrReaderPresenter;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class QrReaderModule {

    @PresentationScope
    @Binds
    abstract QrReaderContract.Presenter presenter (QrReaderPresenter presenter);

    @PresentationScope
    @Binds
    abstract QrReaderContract.View view (QrReaderFragment viewImpl);


}
