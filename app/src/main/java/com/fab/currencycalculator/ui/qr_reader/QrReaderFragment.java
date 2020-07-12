package com.fab.currencycalculator.ui.qr_reader;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.fab.currencycalculator.R;
import com.fab.currencycalculator.ui.base.BaseFragment;

public class QrReaderFragment extends BaseFragment {

    @Override
    public int getLayout () {
        return R.layout.fragment_dashboard;
    }

    @Override
    protected void injectDependencies () {
        //TODO
    }

    @Override
    protected void onCreateFragment () {

    }

}