package com.example.healthtemiapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();

    private CheckBox cb1;
    private RadioGroup rg;
    private RadioButton pickle,source;
    private ImageView image;//asdsadsd
    private int a;

    private Button btn;
    private Button chartBtn;
    private Button jsonBtn;
    private int c;


    private TextView tv_sum,tv_price,tv_c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chartBtn = (Button) findViewById((R.id.chartBtn));
        jsonBtn = (Button) findViewById((R.id.jsonBtn));

        EditText editText = (EditText)findViewById(R.id.nameBox);
        editText.setPrivateImeOptions("defaultInputmode=korean; ");

        chartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity.this, ChartActivity.class);
                startActivity(myIntent);
                finish();
            }
        });
        jsonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                CreateSampleData(name);
            }
        });

        databaseReference.child("gym_user").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                UserList.userList.put(snapshot.getKey(), new UserData());
                AddListenerToUser(snapshot.getKey());
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void CreateSampleData(String name) {
        Calendar cal = Calendar.getInstance();
        for (int i = 1; i <= 15; i++) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
            String befDate = new SimpleDateFormat("MM-dd").format(cal.getTime());
            int min = 60;
            int max = 120;
            databaseReference.child("gym_user").child(name).child(befDate).setValue(new HealthData(0));
        }
    }

    public void AddListenerToUser(String name){
        databaseReference.child("gym_user").child(name).addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String key = snapshot.getKey();
                HealthData healthData = snapshot.getValue(HealthData.class);
                UserList.userList.get(name).AddHealthData(snapshot.getKey(), healthData);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

