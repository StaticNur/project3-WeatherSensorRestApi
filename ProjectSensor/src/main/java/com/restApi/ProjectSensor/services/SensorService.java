package com.restApi.ProjectSensor.services;

import com.restApi.ProjectSensor.models.Sensor;
import com.restApi.ProjectSensor.repositories.SensorRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class SensorService {
    private final SensorRepositories sensorRepositories;
    @Autowired
    public SensorService(SensorRepositories sensorRepositories) {
        this.sensorRepositories = sensorRepositories;
    }
    public Sensor findByName(String name){
        return sensorRepositories.findByName(name);
    }
    @Transactional
    public void save(Sensor sensor){
        enrichAtSensor(sensor);
        sensorRepositories.save(sensor);
    }
    private void enrichAtSensor(Sensor sensor){
        //обогашения
    }
}
