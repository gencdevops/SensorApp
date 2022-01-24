package com.sensorapp.sensorapp.data.dal;

import com.sensorapp.sensorapp.data.entity.Sensor;
import com.sensorapp.sensorapp.data.repository.SensorDataRepository;
import com.sensorapp.sensorapp.data.repository.SensorRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static org.csystem.util.data.DatabaseUtil.*;

@Component
public class SensorServiceHelper {
    private final SensorRepository sensorRepository;
    private final SensorDataRepository sensorDataRepository;

    public SensorServiceHelper(SensorRepository sensorRepository, SensorDataRepository sensorDataRepository) {
        this.sensorRepository = sensorRepository;
        this.sensorDataRepository = sensorDataRepository;
    }

    public Iterable<Sensor> findAllSensors() { // ileride webflux olacak
    return doWorkForRepository(sensorRepository::findAll, "SensorServiceHelper.findAllSensors");
    }

    public Optional<Sensor> findSensorByName(String name) {
       return doWorkForRepository(() -> sensorRepository.findByName(name) , "SensorServiceHelper.findSensorByName");
    }

    public Iterable<Sensor> findSensorByNameContains(String text) {
        return doWorkForRepository(() -> sensorRepository.findByNameContains(text) , "SensorServiceHelper.findSensorByNameContains");
    }

}
