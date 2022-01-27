package com.sensorapp.sensorapp.dto.mapper;


import com.sensorapp.sensorapp.data.entity.SensorData;
import com.sensorapp.sensorapp.dto.SensorDataDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(implementationName = "SensorDataMapperImpl", componentModel = "spring")
public interface SensorDataMapper {
    @Mapping(source = "value", target = "data")
    @Mapping(source = "readDateTime", target = "dataDateTime", dateFormat = "dd/MM/yyyy hh:mm:ss")
    SensorDataDTO toSensorDataDTO(SensorData sensorData);

    @Mapping(target = "value", source = "data")
    @Mapping(target = "readDateTime", source = "dataDateTime", dateFormat = "dd/MM/yyyy hh:mm:ss")
    SensorData toSensorData(SensorDataDTO sensorDataDTO);

    Set<SensorDataDTO> toSensorDataDTOs(Set<SensorData> sensorData);
}
