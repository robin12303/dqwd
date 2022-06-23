package com.example.healthtemiapplication;

import java.util.concurrent.ThreadLocalRandom;

public class Exercise {
    private int sets;

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public float getRest_time() {
        return rest_time;
    }

    public void setRest_time(int rest_time) {
        this.rest_time = rest_time;
    }

    public float getWork_time() {
        return work_time;
    }

    public void setWork_time(int work_time) {
        this.work_time = work_time;
    }

    private float rest_time;
    private float work_time;
    public Exercise(){};
    public void SetRandomValue(int min, int max){
        this.sets = ThreadLocalRandom.current().nextInt(min, max+ 1);
        this.rest_time = ThreadLocalRandom.current().nextInt(min, max);
        this.work_time = ThreadLocalRandom.current().nextInt(min, max);
    }

    public void SetBaseValue(int min){
        this.sets = ThreadLocalRandom.current().nextInt(min, 1);
        this.rest_time = ThreadLocalRandom.current().nextInt(0, 1);
        this.work_time = ThreadLocalRandom.current().nextInt(0, 1);
    }
}
