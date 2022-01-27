package com.sensorapp.sensorapp.dto;

import java.util.Set;

public class SensorDataDTO {
    private double data;
    private String dataDateTime;


    public double getData() {
        return data;
    }

    public void setData(double data) {
        this.data = data;
    }

    public String getDataDateTime() {
        return dataDateTime;
    }

    public void setDataDateTime(String dataDateTime) {
        this.dataDateTime = dataDateTime;
    }
}
