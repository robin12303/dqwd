package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

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

public class MainActivity extends AppCompatActivity {
    private CheckBox cb1;
    private RadioGroup rg;
    private RadioButton pickle,source;
    private ImageView image;

    private Button btn;
    private int c;

    private EditText et_pizza,et_spa,et_sal;

    private TextView tv_sum,tv_price,tv_c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cb1 = (CheckBox)findViewById(R.id.checkBox);
        rg = (RadioGroup) findViewById(R.id.radiogroup);
        pickle =  (RadioButton) findViewById((R.id.radioButtonpic));
        source = (RadioButton) findViewById((R.id.radioButtonsource));
        image = (ImageView) findViewById(R.id.imageView);

        et_pizza = (EditText) findViewById(R.id.editText_pizza);
        et_spa = (EditText) findViewById(R.id.editText_spagati);
        et_sal = (EditText) findViewById(R.id.editTextT_salad);

        tv_sum = (TextView) findViewById((R.id.textView_sum));
        tv_price = (TextView) findViewById((R.id.textView_price));
        tv_c = (TextView) findViewById((R.id.textView_choice));

        btn = (Button)findViewById((R.id.button));

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                if(i==R.id.radioButtonpic)
                {

                    image.setImageResource(R.drawable.p);
                    
                    tv_c.setText("피클을 선택하셨습니다.");


                }
                else
                {
                    image.setImageResource(R.drawable.s);
                    tv_c.setText("소스를 선택하셨습니다.");

                }


            }
        });






        btn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                int sum = 0 ;
                double price = 0;

                et_pizza.getText().toString();
                sum += Integer.parseInt((et_pizza.getText().toString()));
                sum += Integer.parseInt((et_spa.getText().toString()));
                sum += Integer.parseInt((et_sal.getText().toString()));

                price += Integer.parseInt((et_pizza.getText().toString())) * 16000;
                price += Integer.parseInt((et_spa.getText().toString())) * 11000;
                price += Integer.parseInt((et_sal.getText().toString())) * 4000;
                
                tv_sum.setText("주문개수 : " + sum + "개");



                if(cb1.isChecked())
                {
                    double discount = price * 0.07;
                    price = price - discount;


                }
                tv_price.setText("주믄 금액 : " + price );

            }


        });





    }
}

