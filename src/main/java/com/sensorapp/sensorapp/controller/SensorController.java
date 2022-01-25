package com.sensorapp.sensorapp.controller;

import com.sensorapp.sensorapp.dto.SensorDTO;
import com.sensorapp.sensorapp.dto.SensorInfoNotFoundDto;
import com.sensorapp.sensorapp.service.SensorAppService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("api/sensors")
public class SensorController {
    private final SensorAppService sensorAppService;

    public SensorController(SensorAppService sensorAppService) {
        this.sensorAppService = sensorAppService;
    }

    @GetMapping("/sensor/name")
    public Object findSensorByName(String name) {
        var optSensor = sensorAppService.findSensorByName(name);
    return optSensor.isPresent() ? optSensor : new SensorInfoNotFoundDto(name , "Sensor not found");
    }

    @GetMapping("/sensor/contains")
    public Iterable<SensorDTO> findSensorsByNameContains(String text) {
    return sensorAppService.findSensorByNameContains(text);
    }



}
