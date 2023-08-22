package com.restApi.ProjectSensor.util.validators;

import com.restApi.ProjectSensor.dto.MeasurementDTO;
import com.restApi.ProjectSensor.dto.SensorDTO;
import com.restApi.ProjectSensor.models.Measurement;
import com.restApi.ProjectSensor.models.Sensor;
import com.restApi.ProjectSensor.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
@Component
public class SensorValidator implements Validator {
    private final SensorService sensorService;
    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return sensorService.equals(clazz);
    }
    @Override
    public void validate(Object target, Errors errors) {
        SensorDTO sensorDTO = (SensorDTO) target;
        if(sensorService.findByName(sensorDTO.getName()) != null)
            errors.reject("duplicate","Сенсор с таким названием уже существует!");
    }
}
