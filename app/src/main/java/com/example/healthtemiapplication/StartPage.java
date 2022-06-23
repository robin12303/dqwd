package com.example.healthtemiapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;

public class StartPage extends AppCompatActivity {

    private boolean rfidtagged=false;
    private TextView welcomeTextView;
    private ImageView gif_img, gifwaiting_img;
    private Button start;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_page);
        welcomeTextView= findViewById(R.id.txtwelcome);
        gif_img=findViewById(R.id.robot_gif_img);
        gifwaiting_img=findViewById(R.id.robot_waiting_gif_img);
        start = (Button) findViewById(R.id.buttonstart);
        Glide.with(this).load(R.raw.robotgif).into(gif_img);
        Glide.with(this).load(R.raw.robotwelcome).into(gifwaiting_img);
        gifwaiting_img.setVisibility(View.VISIBLE);
        gif_img.setVisibility(View.INVISIBLE);
        start.setVisibility(View.INVISIBLE);
//        if (rfidtagged == true)
//        {
//            welcomeTextView.setText("백종원님, 안녕하세요!");
//
//        }else
//        {
//            smileyface.setImageResource(R.drawable.button_heart);
//        }

        start.setOnClickListener(new View.OnClickListener() // if button clicked
        {
            @Override
            public void onClick(View v)
            {
                changeActivity();
            }
        });
        Calendar cal = Calendar.getInstance();
        String todayDate = new SimpleDateFormat("MM-dd").format(cal.getTime());
        // listener
        databaseReference.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                // logged in
                if(user.login){
                    User.rootUser = user.name; // 회원 변경w
                    User.rootlogin = user.login;
                    welcomeTextView.setText(User.rootUser+"님, 안녕하세요!");
                    start.setVisibility(View.VISIBLE); //show 'start' button
                    // without waiting
                    gifwaiting_img.setVisibility(View.INVISIBLE);
                    gif_img.setVisibility(View.VISIBLE);
                    start.setVisibility(View.VISIBLE);

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

                    // waiting
//                    try {
//                        Thread.sleep(4000); //1초 대기
//                        changeActivity();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                }
            }




            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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


    public void changeActivity(){
        Intent intent = new Intent(StartPage.this, Main2.class);
        startActivity(intent);

    }
}