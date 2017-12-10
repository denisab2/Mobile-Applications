package com.example.deni.labmobile.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.deni.labmobile.Model.Product;
import com.example.deni.labmobile.dao.AppDB;
import com.example.deni.labmobile.dao.IProductDao;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity {

    List<Product> products = new ArrayList<>();
    private AppDB database;
    private IProductDao iProductDao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BarChart chart = new BarChart(this);
        setContentView(chart);

        database = AppDB.getAppDatabase(this);
        iProductDao = database.productDao();
        products = (ArrayList<Product>) iProductDao.getAll();

        int index = 0;
        ArrayList<BarEntry> entries = new ArrayList<>();
        ArrayList<String> labels = new ArrayList<>();
        for (Product p : products) {
            entries.add(new BarEntry( index,p.getQuantity()));
            labels.add(p.getName());
            index++;
        }
        BarDataSet dataset = new BarDataSet(entries, "# Quantity");
        dataset.setColors(ColorTemplate.COLORFUL_COLORS);
        BarData data = new BarData(dataset);


        chart.setData(data);
        chart.getXAxis().setValueFormatter(new IndexAxisValueFormatter(labels));

    }
}
