package com.example.healthtemiapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment1# new Instance} factory method to
 * create an instance of this fragment.
 */


public class Fragment3 extends Fragment {

    private View viewf3;
    private Button btngraphdl;
    private Button logoutBtn;
    private Button jsonBtn2;

    PieChart pieChart;
    Switch switchView;
    ImageView lock;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();
    TextView txthomeusername;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        viewf3 = inflater.inflate(R.layout.fragment_3, container, false);

        btngraphdl = (Button) viewf3.findViewById(R.id.graphdetails);
        lock = (ImageView) viewf3.findViewById(R.id.btnunlocked);
        logoutBtn = (Button) viewf3.findViewById(R.id.logoutBtn);
        jsonBtn2 = (Button) viewf3.findViewById(R.id.jsonBtn2);

        pieChart = (PieChart) viewf3.findViewById(R.id.piechart);
        pieChart.setNoDataText("It is your fist visit");
        switchView = viewf3.findViewById(R.id.switchlock);
        txthomeusername = (TextView) viewf3.findViewById(R.id.txthomeusername);

        txthomeusername.setText(User.rootUser);

        //sample
        jsonBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CreateSampleData(User.rootUser);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(User.rootlogin == false){
                    Intent intent = new Intent(getActivity(), StartPage.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(), "회원증을 찍어주세요!", Toast.LENGTH_LONG).show();
                }
            }
        });


        switchView.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lock.setImageResource(R.drawable.lock_closed);
                    Toast.makeText(getActivity(), "사물함이 잠겼습니다", Toast.LENGTH_LONG).show();
                    // lock == true
                    databaseReference.child("sensor").child("lock").setValue(true);
                } else {
                    lock.setImageResource(R.drawable.lock_open);
                    Toast.makeText(getActivity(), "사물함이 열렸습니다", Toast.LENGTH_LONG).show();
                    // lock == false
                    databaseReference.child("sensor").child("lock").setValue(false);
                }
            }

         });

        btngraphdl.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getActivity(), HomeSelectgraph.class);
                startActivity(intent);
            }
        });
        String s = User.rootUser;
        ArrayList<String> uList = new ArrayList<>(UserList.userList.keySet());
        Calendar cal = Calendar.getInstance();
        String todayDate = new SimpleDateFormat("MM-dd").format(cal.getTime());
        if(uList.contains(User.rootUser)){
            CreatePieChart(UserList.userList.get(User.rootUser).healthDatas);
        } else{
            // 첫방문
            databaseReference.child("gym_user").child(User.rootUser).child(todayDate).setValue(new HealthData(0));
        }

        // Inflate the layout for this fragment
        return viewf3;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

    }



    public void CreatePieChart(Map<String, HealthData> healthDatas)  {
        ArrayList pie = new ArrayList();

        ArrayList<String> date_data = new ArrayList<>(healthDatas.keySet());
        Collections.sort(date_data);
        // in data order(from recent)
        Collections.reverse(date_data);

        float i =0, j=0, k=0, l=0;
        // add total exercise sets
        for(String key : date_data){
            i += healthDatas.get(key).getPushup().getSets();
            j += healthDatas.get(key).getBenchpress().getSets();
            k += healthDatas.get(key).getDeadlift().getSets();
            l += healthDatas.get(key).getBiceps().getSets();
        }
        float t = i + j + k;

        pie.add(new PieEntry(i,"PushUp"));
        pie.add(new PieEntry(j,"BenchPress"));
        pie.add(new PieEntry(k,"DeadLift"));
        pie.add(new PieEntry(l,"Biceps"));

        PieDataSet dataSet = new PieDataSet(pie, "Total exercise ratio");

        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieChart.animateXY(2000, 2000);
        pieChart.invalidate();
    }

    public void CreateSampleData(String name) {
        Calendar cal = Calendar.getInstance();
        for (int i = 1; i <= 5; i++) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
            String befDate = new SimpleDateFormat("MM-dd").format(cal.getTime());
            int min = 60;
            int max = 120;
            databaseReference.child("gym_user").child(name).child(befDate).setValue(new HealthData(0,10));
        }
    }
}