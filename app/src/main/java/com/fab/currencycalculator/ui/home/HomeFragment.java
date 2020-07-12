package com.fab.currencycalculator.ui.home;

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

public class HomeFragment extends BaseFragment {

    @Override
    public int getLayout () {
        return R.layout.fragment_home;
    }

    @Override
    protected void injectDependencies () {

    }

    @Override
    protected void onCreateFragment () {

    }
}