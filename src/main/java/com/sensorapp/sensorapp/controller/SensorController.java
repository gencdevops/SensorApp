package com.sensorapp.sensorapp.controller;

import com.sensorapp.sensorapp.dto.SensorDTO;
import com.sensorapp.sensorapp.dto.SensorInfoNotFoundDto;
import com.sensorapp.sensorapp.dto.SensorsDTO;
import com.sensorapp.sensorapp.service.SensorAppService;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("api/sensors")
public class SensorController {
    private final SensorAppService sensorAppService;

    public SensorController(SensorAppService sensorAppService) {
        this.sensorAppService = sensorAppService;
    }

    @GetMapping("sensor/all")
    public List<SensorDTO> findAllSensors()
    {
        return sensorAppService.findAllSensors();
    }

    @GetMapping("sensor/name")
    public Object findSensorByName(String name)
    {
        var so = sensorAppService.findSensorByName(name);

        return so.isPresent() ? so : new SensorInfoNotFoundDto(name, "Sensor not found");
    }

    @GetMapping("sensor/contains")
    public Iterable<SensorDTO> findSensorsByNameContains(String text)
    {
        return sensorAppService.findSensorByNameContains(text);
    }

    @GetMapping("sensor/detail/contains")
    public SensorsDTO findSensorsByNameContainsDetail(String text)
    {
        return sensorAppService.findSensorByNameContainsDetail(text);
    }


}
