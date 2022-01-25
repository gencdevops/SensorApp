package com.sensorapp.sensorapp.data.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sensor_data")
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sensor_data_id")
    private long id;

    @Column(nullable = false)
    private double value;

    //MapStructla Jsona cevirildi
    @Column(name = "read_date_time", nullable = false)
    private LocalDate readDateTime;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor_id", nullable = false)
    //@JsonIgnore
    private Sensor sensor;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public LocalDate getReadDateTime() {
        return readDateTime;
    }

    public void setReadDateTime(LocalDate readDateTime) {
        this.readDateTime = readDateTime;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
