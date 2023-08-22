package com.restApi.ProjectSensor.repositories;

import com.restApi.ProjectSensor.models.Sensor;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorRepositories extends JpaRepository<Sensor, Integer> {
    Sensor findByName(String name);
}
