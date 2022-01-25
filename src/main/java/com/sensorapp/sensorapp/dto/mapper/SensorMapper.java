package com.sensorapp.sensorapp.dto.mapper;

import com.sensorapp.sensorapp.data.entity.Sensor;
import com.sensorapp.sensorapp.dto.SensorDTO;
import org.mapstruct.Mapper;

@Mapper(implementationName = "SensorMapperImpl" , componentModel = "spring")// Component anatosyonunu koysun diye componentModel = "spring" ekledik
public interface SensorMapper {
    Sensor toSensor(SensorDTO sensorDTO);
    SensorDTO toSensorDTO(Sensor sensor);
}
