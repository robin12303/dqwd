package com.example.healthtemiapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UserData {
    public HashMap<String,HealthData> healthDatas;

    public UserData(){
        this.healthDatas = new HashMap<>();
    };

    public void AddHealthData(String date, HealthData healthData){
        this.healthDatas.put(date, healthData);
    }
}
