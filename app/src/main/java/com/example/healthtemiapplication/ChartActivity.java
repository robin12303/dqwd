package com.example.healthtemiapplication;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collections;
import java.util.Map;

public class ChartActivity extends AppCompatActivity {
    BarChart barChart;
    PieChart pieChart;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        SearchView search_view = (SearchView) findViewById(R.id.search_view);

        search_view.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if(UserList.userList.keySet().contains(query)){
                    name = query;
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        barChart = (BarChart) findViewById(R.id.barchart);
        barChart.setNoDataText("No Data");
        pieChart = (PieChart) findViewById(R.id.piechart);
        pieChart.setNoDataText("No Data");
        Button pushUpBtn = findViewById(R.id.pushUpBtn);
        Button benchPressBtn = findViewById(R.id.benchPressBtn);
        Button deadLiftBtn = findViewById(R.id.deadLiftBtn);
        Button pieBtn = findViewById(R.id.pieBtn);

        pushUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name != null){
                    CreateChart(UserList.userList.get(name).healthDatas,"pushup");
                }
            }
        });
        benchPressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name != null){
                    CreateChart(UserList.userList.get(name).healthDatas,"benchpress");
                }
            }
        });
        deadLiftBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name != null){
                    CreateChart(UserList.userList.get(name).healthDatas,"deadlift");
                }
            }
        });

        pieBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name != null) {
                    CreatePieChart(UserList.userList.get(name).healthDatas);
                }
            }
        });
    }
    public void CreatePieChart(Map<String, HealthData> healthDatas)  {
        ArrayList pie = new ArrayList();

        ArrayList<String> date_data = new ArrayList<>(healthDatas.keySet());
        Collections.sort(date_data);
        // in data order(from recent)
        Collections.reverse(date_data);

        float i =0, j=0, k=0;
        // add total exercise sets
        for(String key : date_data){
            i += healthDatas.get(key).getPushup().getSets();
            j += healthDatas.get(key).getBenchpress().getSets();
            k += healthDatas.get(key).getDeadlift().getSets();
        }
        float t = i + j + k;

        pie.add(new PieEntry(i,"PushUp"));
        pie.add(new PieEntry(j,"BenchPress"));
        pie.add(new PieEntry(k,"DeadLift"));

        PieDataSet dataSet = new PieDataSet(pie, "Total exercise ratio");

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.animateXY(2000, 2000);
        pieChart.invalidate();
    }

    public void CreateChart(Map<String, HealthData> healthDatas, String exercise){
        ArrayList<BarEntry> sets = new ArrayList<>();
        ArrayList <BarEntry> rests = new ArrayList<>();
        ArrayList <BarEntry> works = new ArrayList<>();
        ArrayList<String> date_data = new ArrayList<>(healthDatas.keySet());
        Collections.sort(date_data);
        ArrayList<String> xLabel = new ArrayList<>();

        if(date_data.size() >= 5){
            for(int i = date_data.size() - 5; i < date_data.size() ; i ++){
                xLabel.add(date_data.get(i));
            }
        } else{
            for(int i = 0 ; i < date_data.size() ; i ++){
                xLabel.add(date_data.get(i));
            }
        }

        int i = 0;
        switch (exercise){
            case "pushup":
                for (String key :
                        xLabel) {
                    sets.add(new BarEntry(i, healthDatas.get(key).getPushup().getSets()));
                    rests.add(new BarEntry(i, healthDatas.get(key).getPushup().getRest_time()));
                    works.add(new BarEntry(i, healthDatas.get(key).getPushup().getWork_time()));
                    i++;
                }
                break;
            case "benchpress":
                for (String key :
                        xLabel) {
                    sets.add(new BarEntry(i, healthDatas.get(key).getBenchpress().getSets()));
                    rests.add(new BarEntry(i, healthDatas.get(key).getBenchpress().getRest_time()));
                    works.add(new BarEntry(i, healthDatas.get(key).getBenchpress().getWork_time()));
                    i++;
                }
                break;
            case "deadlift":
                for (String key :
                        xLabel) {
                    sets.add(new BarEntry(i, healthDatas.get(key).getDeadlift().getSets()));
                    rests.add(new BarEntry(i, healthDatas.get(key).getDeadlift().getRest_time()));
                    works.add(new BarEntry(i, healthDatas.get(key).getDeadlift().getWork_time()));
                    i++;
                }
                break;
            default:
                // no exercise
                return;
        }

        BarDataSet set1 = new BarDataSet(sets, "sets");
        BarDataSet set2 = new BarDataSet(rests, "rests");
        BarDataSet set3 = new BarDataSet(works, "works");
        set1.setColors(Color.argb(255,254,163,138));
        set2.setColors(Color.argb(255,194, 255, 208));
        set3.setColors(Color.argb(255,194, 250, 255));
//        set1.setValueTextColor(Color.BLACK);
//        set1.setValueTextSize(16f);

        ArrayList<IBarDataSet> dataSets = new ArrayList<IBarDataSet>();
        dataSets.add(set1);
        dataSets.add(set2);
        dataSets.add(set3);

        BarData barData = new BarData(dataSets);

        barChart.setScaleEnabled(false);
        barChart.setFitBars(true);
        barChart.setData(barData);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        xAxis.setGranularity(1);
        xAxis.setCenterAxisLabels(true);
        xAxis.setAxisMaximum(xLabel.size());

        xAxis.setTextSize(10f);

        float groupSpace = 0.11f;
        float barSpace = 0.01f;
        float barWidth = 0.286f;
        barData.setBarWidth(barWidth);
        barChart.groupBars(0f,groupSpace,barSpace);
        barChart.getDescription().setEnabled(false);
        barChart.setDrawValueAboveBar(false);
        barChart.setTouchEnabled(false);
        barChart.getXAxis().setValueFormatter(new com.github.mikephil.charting.formatter.IndexAxisValueFormatter(xLabel));
        barChart.animateY(1000);
        barChart.invalidate();
    }

}

