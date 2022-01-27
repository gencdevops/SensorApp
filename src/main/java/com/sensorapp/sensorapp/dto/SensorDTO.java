package com.sensorapp.sensorapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.util.Set;

public class SensorDTO {
    private String name;
    private LocalDate registerDate;
    private boolean active;
    private Set<SensorDataDTO> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<SensorDataDTO> getData() {
        return data;
    }
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public void setData(Set<SensorDataDTO> data) {
        this.data = data;
    }
}
