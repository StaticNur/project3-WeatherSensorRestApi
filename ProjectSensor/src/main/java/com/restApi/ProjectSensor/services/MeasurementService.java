package com.restApi.ProjectSensor.services;

import com.restApi.ProjectSensor.models.Measurement;
import com.restApi.ProjectSensor.repositories.MeasurementRepositories;
import com.restApi.ProjectSensor.repositories.SensorRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class MeasurementService {
    private final MeasurementRepositories measurementRepositories;
    private final SensorRepositories sensorRepositories;
    @Autowired
    public MeasurementService(MeasurementRepositories measurementRepositories, SensorRepositories sensorRepositories) {
        this.measurementRepositories = measurementRepositories;
        this.sensorRepositories = sensorRepositories;
    }
    public List<Measurement> findAll(){
        return measurementRepositories.findAll();
    }
    @Transactional
    public void save(Measurement measurement){
        enrichAtMeasurement(measurement);
        measurementRepositories.save(measurement);
    }
    private void enrichAtMeasurement(Measurement measurement){
        measurement.setSensor(sensorRepositories.findByName(measurement.getSensor().getName()));
        measurement.setUpdateAt(LocalDateTime.now());
    }
}
