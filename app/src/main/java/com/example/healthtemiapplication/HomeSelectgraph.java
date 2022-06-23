package com.example.healthtemiapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HomeSelectgraph extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_selectgraph);

        ImageButton graphbp = (ImageButton) findViewById(R.id.btnbp3);
        ImageButton graphdl = (ImageButton) findViewById(R.id.btndl3);
        ImageButton graphsq = (ImageButton) findViewById(R.id.btncs3);
        graphbp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), HomeBenchpress.class);
                startActivity(intent);
            }
        });
        graphdl.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), HomeDeadlift.class);
                startActivity(intent);
            }
        });
        graphsq.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(getApplicationContext(), HomeSquat.class);
                startActivity(intent);
            }
        });
    }
}