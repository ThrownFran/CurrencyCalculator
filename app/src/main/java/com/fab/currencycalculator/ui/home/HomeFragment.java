package com.fab.currencycalculator.ui.home;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import com.fab.currencycalculator.BaseApplication;
import com.fab.currencycalculator.R;
import com.fab.currencycalculator.domain.models.Currency;
import com.fab.currencycalculator.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;

public class HomeFragment extends BaseFragment implements HomeContract.View{

    @BindView(R.id.home_spinner_currencies)
    Spinner spinnerCurrencies;
    @BindView(R.id.home_edit_value)
    EditText editCurrentValue;
    @BindView(R.id.home_progress)
    ProgressBar progressBar;
    @BindView(R.id.home_recycler)
    RecyclerView recyclerView;
    @BindView(R.id.home_button_calculate)
    Button buttonCalculate;

    @Inject
    HomeContract.Presenter presenter;
    @Inject
    RateAdapter adapter;

    @Override
    public int getLayout () {
        return R.layout.fragment_home;
    }

    @Override
    protected void onCreateFragment () {
        super.onCreateFragment();
        presenter.onCreate();
    }

    @Override
    public void onDestroyView () {
        presenter.onDestroy();
        super.onDestroyView();
    }

    @Override
    protected void injectDependencies () {
        BaseApplication.getInstance()
                .getAppComponent()
                .plusHome()
                .create(this)
                .inject(this);
    }

    @Override
    public void setupViewComponents (List<Currency> currencyList) {
        setupCurrencySelector(currencyList);
        setupRecyclerValues();
        setupButtonListener();
    }

    private void setupButtonListener () {
        buttonCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                clickCalculate();
            }
        });
    }

    private void clickCalculate () {
        String value = null;
        if(editCurrentValue.getText() != null){
            value = editCurrentValue.getText().toString();
        }

        presenter.clickCalculate(value);
    }

    private void setupRecyclerValues () {
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setNestedScrollingEnabled(false);
    }

    @Override
    public void showProgress () {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage (String errorMessage) {
        Toast.makeText(getContext(),errorMessage,Toast.LENGTH_LONG).show();
    }

    @Override
    public void hideProgress () {
        progressBar.setVisibility(View.GONE);
    }

    private void setupCurrencySelector (List<Currency> currencyList) {
        String[] items = new String[currencyList.size()];

        int i = 0;
        for(Currency currency : currencyList){
            items[i] = currency.getName() + "(" + currency.getCode()+ ")";
            i++;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_spinner_dropdown_item,
                items);

        spinnerCurrencies.setAdapter(adapter);

        spinnerCurrencies.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected (AdapterView<?> parent, View view, int position,
                                        long id) {
                onSelectCurrency(currencyList.get(position));
            }

            @Override
            public void onNothingSelected (AdapterView<?> parent) {}
        });

    }

    private void onSelectCurrency (Currency currency) {
        presenter.onSelectCurrency(currency);
    }

    @Override
    public void notifyListUpdate () {
        adapter.notifyDataSetChanged();
    }
}