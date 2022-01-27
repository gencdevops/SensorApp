package com.sensorapp.sensorapp.dto.mapper;

import com.sensorapp.sensorapp.data.entity.Sensor;
import com.sensorapp.sensorapp.dto.SensorDTO;
import com.sensorapp.sensorapp.dto.SensorsDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(implementationName = "SensorMapperImpl" , componentModel = "spring")// Component anatosyonunu koysun diye componentModel = "spring" ekledik
public interface SensorMapper {
    Sensor toSensor(SensorDTO sensorDTO);
    SensorDTO toSensorDTO(Sensor sensor);

    default SensorsDTO toSensorsDTO(List<SensorDTO> sensors) {
     return new SensorsDTO()
             .setSensors(sensors);
    }
}
