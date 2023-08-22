package com.restApi.ProjectSensor.repositories;

import com.restApi.ProjectSensor.models.Measurement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasurementRepositories extends JpaRepository<Measurement,Integer> {
}
