package com.example.pzdf.jinjie.entity;

import java.io.Serializable;

public class PieData implements Serializable {

    private int color;
    private float angle = 0;
    private float value;
    private float percentage = 0;
    private float CurrentStartAngle = 0;

    public PieData(float value) {
        this.value = value;
    }

    public float getCurrentStartAngle() {
        return CurrentStartAngle;
    }

    public void setCurrentStartAngle(float currentStartAngle) {
        CurrentStartAngle = currentStartAngle;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
