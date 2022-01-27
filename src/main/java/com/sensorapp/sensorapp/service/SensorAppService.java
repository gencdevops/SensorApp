package com.sensorapp.sensorapp.service;

import com.sensorapp.sensorapp.data.dal.SensorServiceHelper;
import com.sensorapp.sensorapp.data.entity.Sensor;
import com.sensorapp.sensorapp.dto.SensorDTO;
import com.sensorapp.sensorapp.dto.SensorsDTO;
import com.sensorapp.sensorapp.dto.mapper.SensorDataMapper;
import com.sensorapp.sensorapp.dto.mapper.SensorMapper;
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

    public SensorAppService(SensorServiceHelper sensorServiceHelper, SensorDataMapper sensorDataMapper, SensorMapper sensorMapper) {
        this.sensorServiceHelper = sensorServiceHelper;
        this.sensorDataMapper = sensorDataMapper;
        this.sensorMapper = sensorMapper;
    }

    private static <T, R> List<R> mapToList(Iterable<T> iterable, Function<? super T, R> func, boolean parallel) //Şimdilik Kütüphanede olduğunu varsayınız
    {
        return StreamSupport.stream(iterable.spliterator(), parallel).map(func).collect(Collectors.toList());
    }

    private List<SensorDTO> findAllSensorsCallback()
    {
        return mapToList(sensorServiceHelper.findAllSensors(), sensorMapper::toSensorDTO, true);
    }

    private Optional<SensorDTO> findSensorByNameCallback(String name)
    {
        var so = sensorServiceHelper.findSensorByName(name);

        return Optional.ofNullable(sensorMapper.toSensorDTO(so.isEmpty() ? null : so.get()));
    }

    private List<SensorDTO> findSensorByNameContainsCallback(String text)
    {
        return mapToList(sensorServiceHelper.findSensorByNameContains(text), sensorMapper::toSensorDTO, false);
    }

    private SensorDTO findSensorByNameContainsDetailMapCallback(Sensor sensor)
    {
        var dto = sensorMapper.toSensorDTO(sensor);

        dto.setData(sensorDataMapper.toSensorDataDTOs(sensor.sensorData));

        return dto;
    }

    private SensorsDTO findSensorByNameContainsCallbackDetail(String text)
    {
        return sensorMapper.toSensorsDTO(StreamSupport.stream(sensorServiceHelper.findSensorByNameContains(text).spliterator(), false)
                .map(this::findSensorByNameContainsDetailMapCallback)
                .collect(Collectors.toList()));
    }



    public Optional<SensorDTO> findSensorByName(String name)
    {
        return doWorkForService(() -> findSensorByNameCallback(name), "SensorAppService.findSensorByName");
    }

    public Iterable<SensorDTO> findSensorByNameContains(String text)
    {
        return doWorkForService(() -> findSensorByNameContainsCallback(text), "SensorAppService.findSensorByName");
    }

    public SensorsDTO findSensorByNameContainsDetail(String text)
    {
        return doWorkForService(() -> findSensorByNameContainsCallbackDetail(text),
                "SensorAppService.findSensorByNameContainsDetail");
    }

    public List<SensorDTO> findAllSensors()
    {
        return doWorkForService(this::findAllSensorsCallback, "SensorAppService.findSensorByName");
    }
}
