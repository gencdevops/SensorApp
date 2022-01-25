package com.sensorapp.sensorapp.dto.mapper;


import com.sensorapp.sensorapp.data.entity.SensorData;
import com.sensorapp.sensorapp.dto.SensorDataDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(implementationName = "SensorDataMapperImpl", componentModel = "spring")
public interface SensorDataMapper {
    @Mapping(source = "value", target = "data")
    @Mapping(source = "readDateTime", target = "dataDateTime", dateFormat = "dd/MM/yyyy hh:mm:ss")// entity icerisinde LocalDate time olan readDateTime "dateFormat" ile Dto icerisindeki String ifadeye cevirildi
    SensorDataDTO toSensorDataDTO(SensorData sensorData);

    @Mapping(source = "data", target = "value")
    @Mapping(source = "dataDateTime", target = "readDateTime", dateFormat = "dd/MM/yyyy hh:mm:ss")// entity icerisinde LocalDate time olan readDateTime "dateFormat" ile Dto icerisindeki String ifadeye cevirildi
    SensorData toSensorData(SensorDataDTO sensorDataDTO);
}
