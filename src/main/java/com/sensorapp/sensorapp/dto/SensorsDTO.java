package com.sensorapp.sensorapp.dto;

import java.util.List;

public class SensorsDTO {
    private  List<SensorDTO> sensors;

    public List<SensorDTO> getSensors() {
        return sensors;
    }

    public SensorsDTO setSensors(List<SensorDTO> sensors) {
        return this;
    }
}
