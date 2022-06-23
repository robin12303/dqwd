package com.example.healthtemiapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Spinner;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Routineset extends AppCompatActivity {
    public static Context context_main;
    int t;  //(selected)time
    int w; //weight
    int s; //set
    int n;
    TextView txtweight;
    TextView txtnumber;
    TextView txtset;
    TextView txtbreak;
    String[] weights = {"5kg","10kg", "20kg","30kg", "40kg","50kg"};
    String[] numbers = {"5회", "10회", "20회", "30회", "40회","50회"};
    String[] sets = {"1세트","3세트","5세트","10세트","15세트","20세트"};
    String[] breaks = {"10초","30초","1분","1분 30초","2분","2분 30초","3분"};

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routineset);
        context_main = this;
        Button btnset =(Button) findViewById(R.id.setbuttonNext);
        Button btnprev = (Button) findViewById(R.id. setButtonBack);
        btnset.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                databaseReference.child("sensor").child("infrared").setValue(true);
                Intent intent = new Intent(getApplicationContext(), RoutineCount.class);
                startActivity(intent);
            }
        });
        /*btnprev.setOnClickListener(new View.OnClickListener()
        {

            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), Main2.class);
                startActivity(intent);
            }
        });*/


        Spinner spinnerweight = findViewById(R.id.arrayweight);
        //txtweight=findViewById(R.id.textweight);

        Spinner spinnernumber = findViewById(R.id.arraynumber);
        //txtnumber = findViewById(R.id.textnumber);

        Spinner spinnerset = findViewById(R.id.arrayset);
        //txtset = findViewById(R.id.textset);

        Spinner spinnerbreak = findViewById(R.id.arraybreak);
        //txtbreak = findViewById(R.id.textbreak);

        ArrayAdapter<String> adapterweight = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item, weights
        );
        ArrayAdapter<String> adapternumber = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item, numbers
        );
        ArrayAdapter<String> adapterset = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item, sets
        );
        ArrayAdapter<String> adapterbreak = new ArrayAdapter<String>(
                this,android.R.layout.simple_spinner_item, breaks
        );

        adapterweight.setDropDownViewResource(android.R.layout.simple_spinner_item);
        adapternumber.setDropDownViewResource(android.R.layout.simple_spinner_item);
        adapterset.setDropDownViewResource(android.R.layout.simple_spinner_item);
        adapterbreak.setDropDownViewResource(android.R.layout.simple_spinner_item);


        spinnerweight.setAdapter(adapterweight);
        spinnernumber.setAdapter(adapternumber);
        spinnerset.setAdapter(adapterset);
        spinnerbreak.setAdapter(adapterbreak);


        spinnerweight.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (position==0) w = 5;
                else if (position==1) w =10;
                else if (position==2) w =20;
                else if (position==3) w =30;
                else if (position==4) w =40;
                else if (position==5) w =50;
                //String strw=weights[position].toString();
                //w = Integer.parseInt(strw);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                w=5;
            }
        });
        spinnernumber.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (position==0) n = 5;
                else if (position==1) n =10;
                else if (position==2) n =20;
                else if (position==3) n =30;
                else if (position==4) n =40;
                else if (position==5) n =50;
                //String strn=numbers[position].toString();
                //n = Integer.parseInt(strn);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                n=5;
            }
        });
        spinnerset.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (position==0) s = 1;
                else if (position==1) s =3;
                else if (position==2) s =5;
                else if (position==3) s =10;
                else if (position==4) s =15;
                else if (position==5) s =20;
                //String strs=sets[position].toString();
                //s=Integer.parseInt(strs);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
                s=1;
            }
        });
        spinnerbreak.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                if (position ==0) t=1;
                else if (position==1) t = 3;
                else if (position==2) t =6;
                else if (position==3) t =9;
                else if (position==4) t =12;
                else if (position==5) t =15;
                else if (position==6) t =18;

                //String strt = breaks[position].toString();
                //t =Integer.parseInt(strt);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

                t=3;
            }
        });


    }
}










