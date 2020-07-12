package com.fab.currencycalculator.ui.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;

public abstract class BaseFragment extends Fragment {

    public abstract int getLayout ();
    protected void injectDependencies (){}
    protected void onCreateFragment (){}

    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(getLayout(), container, false);
        ButterKnife.bind(this,root);
        injectDependencies();
        onCreateFragment();
        return root;
    }
}
