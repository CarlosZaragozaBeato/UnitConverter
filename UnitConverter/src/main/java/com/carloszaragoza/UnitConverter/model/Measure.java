package com.carloszaragoza.UnitConverter.model;

public class Measure {
    private String prevMeasure;
    private String finalMeasure;
    private double oldValue;
    private double newValue;

    public Measure(String prevMeasure, double oldValue, double newValue, String finalMeasure) {
        this.prevMeasure = prevMeasure;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.finalMeasure = finalMeasure;
    }

    public String getPrevMeasure() {
        return prevMeasure;
    }

    public void setPrevMeasure(String prevMeasure) {
        this.prevMeasure = prevMeasure;
    }

    public double getNewValue() {
        return newValue;
    }

    public void setNewValue(double newValue) {
        this.newValue = newValue;
    }

    public double getOldValue() {
        return oldValue;
    }

    public void setOldValue(double oldValue) {
        this.oldValue = oldValue;
    }

    public String getFinalMeasure() {
        return finalMeasure;
    }

    public void setFinalMeasure(String finalMeasure) {
        this.finalMeasure = finalMeasure;
    }

}
