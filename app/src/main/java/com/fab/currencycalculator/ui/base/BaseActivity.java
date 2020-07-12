package com.fab.currencycalculator.ui.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;

public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void injectDependencies ();
    public abstract int getLayout ();

    @Override
    protected void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        injectDependencies();
    }


}
