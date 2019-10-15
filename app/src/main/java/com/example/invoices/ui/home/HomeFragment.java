package com.example.invoices.ui.home;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.invoices.Invoice;
import com.example.invoices.MainActivity;
import com.example.invoices.R;
import com.example.invoices.Resource;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Call<Resource> call = MainActivity.service.resource(MainActivity.appPreference.getDisplayJwt());

//calling the api
        call.enqueue(new Callback<Resource>() {
            @Override
            public void onResponse(Call<Resource> call, Response<Resource> response) {

                PieChart pieChart = (PieChart) root.findViewById(R.id.pieChart);
                pieChart.setUsePercentValues(true);


                pieChart.getDescription().setEnabled(false);
                pieChart.setExtraOffsets(5,10,5,5);

                pieChart.setDragDecelerationFrictionCoef(0.99f);

                pieChart.setDrawHoleEnabled(true);
                pieChart.setHoleColor(Color.WHITE);
                pieChart.setTransparentCircleRadius(60f);

                final ArrayList<PieEntry> yValues = new ArrayList<>();

                yValues.add(new PieEntry(response.body().getInvoices(),"Faktury Sprzedaży"));
                yValues.add(new PieEntry(response.body().getServices(),"Pozycje na fakturze sprzedaży"));
                yValues.add(new PieEntry(response.body().getCustomers(),"Klienci"));
                yValues.add(new PieEntry(response.body().getInvoicesP(),"Faktury Zakupu"));
                yValues.add(new PieEntry(response.body().getServicesP(),"Pozycje na fakturze zakupu"));
                yValues.add(new PieEntry(response.body().getSuppliers(),"Dostawcy"));

                pieChart.animateY(1000, Easing.EaseInOutCubic);

                //pieChart.getLegend().setEnabled(false);

                pieChart.getLegend().setWordWrapEnabled(true);

                PieDataSet dataSet = new PieDataSet(yValues, "Data");

                dataSet.setSliceSpace(3f);
                dataSet.setSelectionShift(5f);
                dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

                PieData data = new PieData(dataSet);
                data.setValueTextSize(15);
                data.setValueTextColor(Color.BLACK);
                pieChart.setData(data);

                pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                    @Override
                    public void onValueSelected(Entry e, Highlight h) {
                        System.out.println(e.toString());
                        System.out.println(h.toString());

                        int pos1 = e.toString().indexOf("y:");
                        String ilosc = e.toString().substring(pos1 + 3);

                        double d = Double.parseDouble(ilosc);

                        MainActivity.appPreference.showToast("Ilość: " + (int) d);
                    }

                    @Override
                    public void onNothingSelected() {

                    }
                });

        /*List<PieEntry> value = new ArrayList<>();
        value.add(new PieEntry(40f, "Jan"));
        value.add(new PieEntry(60f, "Feb"));

        PieDataSet pieDataSet = new PieDataSet(value, "Months");

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);*/
            } @Override
            public void onFailure(Call<Resource> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

        return root;
    }
}