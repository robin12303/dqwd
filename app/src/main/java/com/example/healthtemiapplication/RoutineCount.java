package com.example.healthtemiapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthtemiapplication.R;
import com.example.healthtemiapplication.Routineset;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.Set;
import java.util.Timer;

public class RoutineCount extends AppCompatActivity {


    static int selectedTime = ((Routineset)Routineset.context_main).t;
    static int selectedSet = ((Routineset)Routineset.context_main).s;
    static int selectedNumber = ((Routineset)Routineset.context_main).n;
    static int selectedWeight = ((Routineset)Routineset.context_main).w;

    private static final long START_TIME_IN_MILLIS = selectedTime*10000;
    private TextView countDownTextView, setTextView, numTextView, nameTextView, weightTextView;
    private Button startPauseTimerBtn,  resetTimerBtn, numberUpBtn, endExerciseBtn;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;
    private static int num = 1;
    private boolean finished=false;
    private int currentSet= 0, currentNum= 0, timerfinished=0;

    private boolean working =false;
    private long workStartTime;
    private long workEndTime;
    private static int  restTime = 0;

    private static int s;
    private static float r,w;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = database.getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_count);
        nameTextView = findViewById(R.id.nameTextView);
        weightTextView= findViewById(R.id.weightTextView);
        countDownTextView = findViewById(R.id.countdownTextView);
        setTextView = findViewById(R.id.countSetTextView);
        numTextView = findViewById(R.id.countNumTextView);
        startPauseTimerBtn = (Button) findViewById(R.id.startPauseTimerBtn);
        resetTimerBtn = (Button) findViewById(R.id.resetTimerBtn);
        numberUpBtn = (Button) findViewById(R.id.numberupBtn);
        endExerciseBtn = (Button) findViewById(R.id.endExerciseBtn);
        //recordTimerBtn = (Button) findViewById(R.id.recordTimerBtn);
        //recordListBtn = (Button) findViewById(R.id.recordListBtn);
        //recordTimeTextView = findViewById(R.id.recordTimeTextView);
        countDownTextView.bringToFront();
        setTextView.bringToFront();
        numTextView.bringToFront();
        nameTextView.setText("데드리프트");
        weightTextView.setText("중량: "+ selectedWeight+ "kg");

        //카운트다운 TextView 업데이트
        updateText();

        //start / pause 버튼 클릭시 /// 첫번째 클릭시 운동시작
        startPauseTimerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!working){
                    working = true;
                    workStartTime = System.currentTimeMillis();
                }
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        numberUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!working){
                    working = true;
                    workStartTime = System.currentTimeMillis();
                }
                if (currentNum < selectedNumber) //현재 횟수가 세팅된 횟수보다 작으면 횟수 증가
                {
                    currentNum++;
                    updateText();
                }



                if (currentNum == selectedNumber && finished ==false)
                {
                    startTimer();

                }

            }
        });

        //reset 버튼 클릭시
        resetTimerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        Calendar cal = Calendar.getInstance();
        String todayDate = new SimpleDateFormat("MM-dd").format(cal.getTime());


        databaseReference.child("gym_user").child(User.rootUser).child(todayDate).child(User.rootExer).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Exercise prevExer = snapshot.getValue(Exercise.class);
                if(prevExer != null){
                    s = prevExer.getSets();
                    r = prevExer.getRest_time();
                    w = prevExer.getWork_time();
                }
                else{
                    s = 0;
                    r = 0;
                    w = 0;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //end 버튼 클릭시
        endExerciseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //시작안누르고 바로 종료 눌렀을 경우 그냥 통과
                if(working){
                    working = false;
                    // 초단위
                    double totalWorkTime =  ( System.currentTimeMillis() - workStartTime) / 1000.0;
                    totalWorkTime =  Math.round((totalWorkTime *100) /100.0);
                    if(totalWorkTime < 0){
                        totalWorkTime = 0;
                    }

                    double totalRestTime = restTime;
                    totalRestTime = Math.round((totalRestTime *100)/100.0);

                    // sets rest_time work_time
                    databaseReference.child("gym_user").child(User.rootUser).child(todayDate).child(User.rootExer).child("sets").setValue(s + currentSet);
                    databaseReference.child("gym_user").child(User.rootUser).child(todayDate).child(User.rootExer).child("rest_time").setValue(r + totalRestTime);
                    databaseReference.child("gym_user").child(User.rootUser).child(todayDate).child(User.rootExer).child("work_time").setValue(w + totalWorkTime);
                }
                
                

                restTime = 0;
                currentSet = 0;
                currentNum = 0;
                finished = true;
                resetTimer();

                // 적외선 센서 끄기
                databaseReference.child("sensor").child("infrared").setValue(false);

                // 창이동
                Intent intent = new Intent(RoutineCount.this, Main2.class);
                startActivity(intent);
            }
        });

/*        //기록버튼 클릭시
        recordTimerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                long recordTime = START_TIME_IN_MILLIS - mTimeLeftInMillis;

                int minutes = (int) (recordTime / 1000) / 60;
                int seconds = (int) (recordTime / 1000) % 60;

                String timeLeftFormatted = String.format(Locale.getDefault(), "약 %02d분 %02d초", minutes, seconds);
                String resultRecordTime = timeLeftFormatted;
                if ("".equals(recordTimeTextView.getText()) || recordTimeTextView.getText() == null) {
                    recordTimeTextView.setText("1회차 : " + recordTimeTextView.getText().toString() + resultRecordTime + "\n");
                } else {
                    recordTimeTextView.setText(recordTimeTextView.getText().toString() + num + "회차 : " + resultRecordTime + "\n");
                }
                num++;
            }
        });*/

  /*      //기록 초기화 버튼 클릭시
        recordListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordTimeTextView.setText("");
                num = 1;
            }
        });*/
    }



    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateText();
                restTime++;
            }

            @Override
            public void onFinish() {

                mTimerRunning = false;
                resetTimer();


                startPauseTimerBtn.setText("시작");
                startPauseTimerBtn.setVisibility(View.INVISIBLE);
                resetTimerBtn.setVisibility(View.VISIBLE);
                endExerciseBtn.setVisibility(View.VISIBLE);
            }
        }.start();



        mTimerRunning = true;
        startPauseTimerBtn.setText("정지");
        resetTimerBtn.setVisibility(View.INVISIBLE);
        endExerciseBtn.setVisibility(View.INVISIBLE);
    }

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        startPauseTimerBtn.setText("시작");
        resetTimerBtn.setVisibility(View.VISIBLE);
        endExerciseBtn.setVisibility(View.VISIBLE);
    }


    private void resetTimer() {
        mTimeLeftInMillis = START_TIME_IN_MILLIS;
        if (finished || (currentSet == selectedSet && currentNum == selectedNumber))
        {
            finished=true;
            Toast.makeText(getApplicationContext(), "수고하셨습니다!", Toast.LENGTH_LONG).show();
        }
        if (currentNum == selectedNumber && finished ==false)
        {
            currentSet++;
            currentNum=0;
        }
        updateText();

        resetTimerBtn.setVisibility(View.INVISIBLE);
        startPauseTimerBtn.setVisibility(View.VISIBLE);

    }




    private void updateText() {
        int minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d : %02d", minutes, seconds);

        setTextView.setText(currentSet+ "/"  + selectedSet);
        numTextView.setText(currentNum+ "/"  + selectedNumber);
        countDownTextView.setText(timeLeftFormatted);

    }


}

