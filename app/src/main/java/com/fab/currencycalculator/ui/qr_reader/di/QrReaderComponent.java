package com.fab.currencycalculator.ui.qr_reader.di;

import com.fab.currencycalculator.dependency_injection.PresentationScope;
import com.fab.currencycalculator.ui.main.MainActivity;
import com.fab.currencycalculator.ui.qr_reader.QrReaderFragment;

import dagger.BindsInstance;
import dagger.Subcomponent;

@PresentationScope
@Subcomponent(modules = {QrReaderModule.class})
public interface QrReaderComponent {
    void inject (QrReaderFragment viewImpl);

    @Subcomponent.Factory
    interface Factory{
        QrReaderComponent create (@BindsInstance QrReaderFragment viewImpl);
    }
}
