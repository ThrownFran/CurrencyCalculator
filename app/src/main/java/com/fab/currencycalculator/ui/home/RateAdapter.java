package com.fab.currencycalculator.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fab.currencycalculator.R;
import com.fab.currencycalculator.domain.models.RateModel;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RateAdapter extends RecyclerView.Adapter<RateAdapter.ViewHolder> {

    HomeContract.Presenter presenter;

    public RateAdapter (HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(ViewHolder.LAYOUT,
                parent, false);
        return new ViewHolder(view,presenter);
    }

    @Override
    public void onBindViewHolder (@NonNull ViewHolder holder, int position) {
        RateModel model = presenter.getRateList().get(position);
        holder.bind(model);
    }

    @Override
    public int getItemCount () {
        return presenter.getRateList().size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public static int LAYOUT = R.layout.item_rate;

        @BindView(R.id.item_rate_text_value)
        TextView textValue;
        @BindView(R.id.item_rate_text_rate)
        TextView textRate;

        private HomeContract.Presenter presenter;

        public ViewHolder (@NonNull View itemView,
                           HomeContract.Presenter presenter) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            this.presenter = presenter;
        }

        public void bind (RateModel model) {
            setValue(model);
            setRate(model);
        }

        private void setRate (RateModel model) {

            RateModel.Result rateResult = model.getValueInThisCurrency(presenter.getCurrentRate());
            float value = rateResult.value;

            textRate.setText(String.valueOf(1));
            textRate.append(" ");
            textRate.append(presenter.getCurrentRate().getCurrency().getName());
            textRate.append(" ");
            textRate.append(String.valueOf(value));
            textRate.append(" ");
            textRate.append(rateResult.currency.getName());
        }

        private void setValue (RateModel model) {

            RateModel.Result rateResult = model.getValueInThisCurrency(presenter.getCurrentRate());
            float value = rateResult.value * presenter.getCurrentValue();

            textValue.setText(String.valueOf(value));
            textValue.append(" ");
            textValue.append(rateResult.currency.getName());
        }
    }
}
