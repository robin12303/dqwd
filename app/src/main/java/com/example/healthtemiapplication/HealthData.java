package com.example.healthtemiapplication;

import java.util.concurrent.ThreadLocalRandom;

public class HealthData {
    public Exercise pushup;

    public Exercise getPushup() {
        return pushup;
    }

    public void setPushup(Exercise pushup) {
        this.pushup = pushup;
    }

    public Exercise getDeadlift() {
        return deadlift;
    }

    public void setDeadlift(Exercise deadlift) {
        this.deadlift = deadlift;
    }

    public Exercise getBenchpress() {
        return benchpress;
    }

    public void setBenchpress(Exercise benchpress) {
        this.benchpress = benchpress;
    }

    public Exercise biceps;

    public Exercise getBiceps() {
        return biceps;
    }

    public void setBiceps(Exercise biceps) {this.biceps = biceps;}

    public Exercise deadlift;
    public Exercise benchpress;
    public HealthData(){};
    public HealthData(int base){
        this.pushup = new Exercise();
        this.deadlift = new Exercise();
        this.benchpress = new Exercise();
        this.pushup.SetBaseValue(base);
        this.deadlift.SetBaseValue(base);
        this.benchpress.SetBaseValue(base);
        this.biceps = new Exercise();
        this.biceps.SetBaseValue(base);
    };

    public HealthData(int down, int up){
        this.pushup = new Exercise();
        this.deadlift = new Exercise();
        this.benchpress = new Exercise();
        this.pushup.SetRandomValue(down, up);
        this.deadlift.SetRandomValue(down, up);
        this.benchpress.SetRandomValue(down, up);
        this.biceps = new Exercise();
        this.biceps.SetRandomValue(down,up);
    };
}
