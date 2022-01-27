package com.sensorapp.sensorapp.service;

import com.sensorapp.sensorapp.data.dal.SensorServiceHelper;
import com.sensorapp.sensorapp.dto.SensorDTO;
import com.sensorapp.sensorapp.dto.mapper.SensorDataMapper;
import com.sensorapp.sensorapp.dto.mapper.SensorMapper;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.csystem.util.data.DatabaseUtil.*;

@Service
public class SensorAppService {
    private final SensorServiceHelper sensorServiceHelper;
    private final SensorDataMapper sensorDataMapper;
    private final SensorMapper sensorMapper;

    private static<T,R> List<R> mapToList(Iterable<T> iterable, Function<? super T, R> func, boolean parallel) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .map(func)
                .collect(Collectors.toList());
    }

    private List<SensorDTO> findAllSensorsCallback() {
        return mapToList(sensorServiceHelper.findAllSensors(), sensorMapper::toSensorDTO, true);
    }

    private Optional<SensorDTO> findSensorByNameCallback(String name) {
        var optSensor = sensorServiceHelper.findSensorByName(name);
        return Optional.ofNullable(sensorMapper.toSensorDTO(optSensor.isEmpty() ? null : optSensor.get()));

    }

    private List<SensorDTO> findSensorByNameContainsCallback(String text) {
        return mapToList(sensorServiceHelper.findSensorByNameContains(text), sensorMapper::toSensorDTO, false);
    }


    public SensorAppService(SensorServiceHelper sensorServiceHelper, SensorDataMapper sensorDataMapper, SensorMapper sensorMapper) {
        this.sensorServiceHelper = sensorServiceHelper;
        this.sensorDataMapper = sensorDataMapper;
        this.sensorMapper = sensorMapper;
    }

   public Optional<SensorDTO> findSensorByName(String name){
    return doWorkForService(() -> findSensorByNameCallback(name), "SensorAppService.findSensorByName");
   }

   public Iterable<SensorDTO> findSensorByNameContains(String text) {
       return doWorkForService(() -> findSensorByNameContainsCallback(text), "SensorAppService.findSensorByNameContains");
   }

   @Profile("dev")
    public List<SensorDTO> findAllSensors() {
        return doWorkForService(this::findAllSensorsCallback, "SensorAppService.findAllSensors");
   }


}
