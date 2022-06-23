package com.example.healthtemiapplication;



import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class TeachingResults extends AppCompatActivity {

    ImageButton heartone, hearttwo, heartthree, heartfour;
    TextView heartoneTextView, hearttwoTextView, heartthreeTextView, heartfourTextView;
    boolean oneclicked= false, twoclicked = false, threeclicked = false, fourclicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teaching_results);

        heartone = findViewById(R.id.btnht1);
        hearttwo = findViewById(R.id.btnht2);
        heartthree = findViewById(R.id.btnht3);
        heartfour = findViewById(R.id.btnht4);
        heartoneTextView = findViewById(R.id.txtheartone);
        hearttwoTextView = findViewById(R.id.txthearttwo);
        heartthreeTextView = findViewById(R.id.txtheartthree);
        heartfourTextView = findViewById(R.id.txtheartfour);
        heartoneTextView.setText("0");
        hearttwoTextView.setText("0");
        heartthreeTextView.setText("0");
        heartfourTextView.setText("0");
        heartone.setImageResource(R.drawable.button_heart);
        hearttwo.setImageResource(R.drawable.button_heart);
        heartthree.setImageResource(R.drawable.button_heart);
        heartfour.setImageResource(R.drawable.button_heart);
    }

    public void heartoneClicked(View v)
    {
        if (oneclicked == true)
        {
            heartone.setImageResource(R.drawable.button_heart);
            heartoneTextView.setText("0");
            oneclicked= false;

        }else
        {

            heartone.setImageResource(R.drawable.button_heart_black);
            heartoneTextView.setText("1");
            oneclicked=true;
        }
    }

    public void hearttwoClicked(View v)
    {
        if (twoclicked == true)
        {
            hearttwo.setImageResource(R.drawable.button_heart);
            hearttwoTextView.setText("0");
            twoclicked= false;

        }else
        {

            hearttwo.setImageResource(R.drawable.button_heart_black);
            hearttwoTextView.setText("1");
            twoclicked=true;
        }
    }

    public void heartthreeClicked(View v)
    {
        if (threeclicked == true)
        {
            heartthree.setImageResource(R.drawable.button_heart);
            heartthreeTextView.setText("0");
            threeclicked= false;

        }else
        {

            heartthree.setImageResource(R.drawable.button_heart_black);
            heartthreeTextView.setText("1");
            threeclicked=true;
        }
    }

    public void heartfourClicked(View v)
    {
        if (fourclicked == true)
        {
            heartfour.setImageResource(R.drawable.button_heart);
            heartfourTextView.setText("0");
            fourclicked= false;

        }else
        {

            heartfour.setImageResource(R.drawable.button_heart_black);
            heartfourTextView.setText("1");
            fourclicked=true;
        }
    }
}