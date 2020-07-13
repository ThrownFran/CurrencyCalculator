package com.fab.currencycalculator.ui.home;

import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

import com.fab.currencycalculator.BaseApplication;
import com.fab.currencycalculator.R;
import com.fab.currencycalculator.data.currency.QrResultJson;
import com.fab.currencycalculator.domain.models.Currency;
import com.fab.currencycalculator.domain.models.RateModel;
import com.fab.currencycalculator.ui.QRGenerator;
import com.fab.currencycalculator.ui.Utils;
import com.fab.currencycalculator.ui.base.BaseFragment;
import com.google.gson.Gson;

import java.util.List;

import javax.inject.Inject;

import static android.content.Context.WINDOW_SERVICE;

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
    @BindView(R.id.home_image_qr)
    ImageView imageQr;

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

    @Override
    public void showCurrencyNotSupportedMessage () {
        Utils.showToast(getActivity(),getString(R.string.currency_not_supported));
    }


    private void clickCalculate () {
        String value = null;
        if(editCurrentValue.getText() != null){
            value = editCurrentValue.getText().toString();
        }

        presenter.clickCalculate(value);

        cleanViews();
    }

    private void cleanViews () {
        Utils.hideSoftKeyboard(getActivity());
        editCurrentValue.clearFocus();
        imageQr.setVisibility(View.GONE);
        buttonCalculate.setText(getString(R.string.calculate));
    }

    @Override
    public void setRecalculateButton () {
        buttonCalculate.setText(getString(R.string.recalculate));
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
        Utils.showToast(getActivity(),errorMessage);
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
        clickCalculate();
    }

    @Override
    public void setCurrency (int position) {
        spinnerCurrencies.setSelection(position);
    }

    @Override
    public void generateQr (String input) {
        QRGenerator qrGenerator = new QRGenerator(input,getSmallQrDimension());
        imageQr.setImageBitmap(qrGenerator.getBitmap());
        imageQr.setVisibility(View.VISIBLE);
    }

    private int getSmallQrDimension () {
        WindowManager manager = (WindowManager) getContext().getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 3 / 4;
        return smallerDimension;
    }

    @Override
    public void setValue (float value) {
        editCurrentValue.setText(String.valueOf(value));
    }

    @Override
    public void notifyListUpdate () {
        adapter.notifyDataSetChanged();
    }
}