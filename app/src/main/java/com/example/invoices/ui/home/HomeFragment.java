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

import com.example.invoices.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        PieChart pieChart = (PieChart) root.findViewById(R.id.pieChart);
        pieChart.setUsePercentValues(true);

        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.99f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(60f);

        ArrayList<PieEntry> yValues = new ArrayList<>();

        yValues.add(new PieEntry(2,"Faktury Sprzedaży"));
        yValues.add(new PieEntry(6,"Pozycje na fakturze sprzedaży"));
        yValues.add(new PieEntry(2,"Klienci"));
        yValues.add(new PieEntry(5,"Faktury Zakupu"));
        yValues.add(new PieEntry(6,"Pozycje na fakturze zakupu"));
        yValues.add(new PieEntry(3,"Dostawcy"));

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

        /*List<PieEntry> value = new ArrayList<>();
        value.add(new PieEntry(40f, "Jan"));
        value.add(new PieEntry(60f, "Feb"));

        PieDataSet pieDataSet = new PieDataSet(value, "Months");

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);*/

        return root;
    }
}