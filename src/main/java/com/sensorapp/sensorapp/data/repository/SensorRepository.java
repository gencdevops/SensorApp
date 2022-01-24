package com.sensorapp.sensorapp.data.repository;

import com.sensorapp.sensorapp.data.entity.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {

    //@Query("select s from Sensor s where s.name like :name")
    Optional<Sensor> findByName(String name);
    //Optional<Sensor> findByNameAndRegisterDate(String name, LocalDate registerDate);
    Iterable<Sensor> findByNameContains(String text);

}
