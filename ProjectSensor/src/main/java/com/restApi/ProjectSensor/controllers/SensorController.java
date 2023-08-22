package com.restApi.ProjectSensor.controllers;

import com.restApi.ProjectSensor.dto.SensorDTO;
import com.restApi.ProjectSensor.models.Sensor;
import com.restApi.ProjectSensor.services.SensorService;
import com.restApi.ProjectSensor.util.errors.SensorErrorResponse;
import com.restApi.ProjectSensor.util.errors.SensorNotRegistrationException;
import com.restApi.ProjectSensor.util.validators.SensorValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {
    private final ModelMapper modelMapper;
    private final SensorService sensorService;
    private final SensorValidator sensorValidator;
    @Autowired
    public SensorController(ModelMapper modelMapper, SensorService sensorService,
                            SensorValidator sensorValidator) {
        this.modelMapper = modelMapper;
        this.sensorService = sensorService;
        this.sensorValidator = sensorValidator;
    }
    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> addSensor(@RequestBody @Valid SensorDTO sensorDTO,
                                                BindingResult bindingResult){
        sensorValidator.validate(sensorDTO,bindingResult);
        if(bindingResult.hasErrors()) {
            String message = sensorDTO.getName()+" - "+
                    bindingResult.getGlobalError().getDefaultMessage()+";";
            throw new SensorNotRegistrationException(message);
        }
        sensorService.save(convertToSensor(sensorDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<SensorErrorResponse> handleException(SensorNotRegistrationException e){
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(sensorErrorResponse,HttpStatus.BAD_REQUEST);
    }
    private Sensor convertToSensor(SensorDTO sensorDTO){
        return modelMapper.map(sensorDTO,Sensor.class);
    }
}
