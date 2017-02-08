package com.feoul.customviewdemo;

import android.support.annotation.NonNull;

/**
 * Created by 5555 on 2017/2/7 0007.
 */

public class CircleData {
    private String name;
    private float value;
    private float percentage;

    private int color = 0;
    private  float angle = 0;

    CircleData(@NonNull String name, @NonNull float value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public float getValue() {
        return value;
    }

    public float getPercentage() {
        return percentage;
    }

    public int getColor() {
        return color;
    }

    public float getAngle() {
        return angle;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
