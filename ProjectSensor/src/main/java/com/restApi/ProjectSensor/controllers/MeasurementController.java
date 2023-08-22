package com.restApi.ProjectSensor.controllers;

import com.restApi.ProjectSensor.dto.MeasurementDTO;
import com.restApi.ProjectSensor.dto.SensorDTO;
import com.restApi.ProjectSensor.models.Measurement;
import com.restApi.ProjectSensor.models.Sensor;
import com.restApi.ProjectSensor.services.MeasurementService;
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
import java.util.stream.Collectors;


@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final ModelMapper modelMapper;
    private final MeasurementService measurementService;
    private final SensorValidator sensorValidator;
    @Autowired
    public MeasurementController(MeasurementService measurementService,
                                 SensorValidator sensorValidator,ModelMapper modelMapper) {
        this.measurementService = measurementService;
        this.sensorValidator = sensorValidator;
        this.modelMapper = modelMapper;
    }
    @GetMapping
    public List<MeasurementDTO> getMeasurements(){
        return measurementService.findAll().stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }
    @GetMapping("/rainyDaysCount")
    public List<MeasurementDTO> getRainyDaysCount(){
        return measurementService.findAll()
                .stream().map(this::convertToMeasurementDTO)
                .filter(measurementDTO -> measurementDTO.getRaining())
                .collect(Collectors.toList());
    }


    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody @Valid MeasurementDTO measurementDTO,
                                          BindingResult bindingResult){
        sensorValidator.validate(measurementDTO.getSensorDTO(),bindingResult);
        if(bindingResult.hasErrors()){
            measurementService.save(convertToMeasurement(measurementDTO));
            return ResponseEntity.ok(HttpStatus.OK);
        }
        String message = measurementDTO.getSensorDTO().getName() +
                " - " + "сенсор с таким названием не зарегистрирован в системе!";
        throw new SensorNotRegistrationException(message);

    }
    @ExceptionHandler
    public ResponseEntity<SensorErrorResponse> handleException(SensorNotRegistrationException e){
        SensorErrorResponse sensorErrorResponse = new SensorErrorResponse(
                e.getMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(sensorErrorResponse,HttpStatus.BAD_REQUEST);
    }
    private Measurement convertToMeasurement(MeasurementDTO measurementDTO){
        Measurement measurement = modelMapper.map(measurementDTO,Measurement.class);
        measurement.getSensor().setName(measurementDTO.getSensorDTO().getName());
        return measurement;
    }
    private MeasurementDTO convertToMeasurementDTO(Measurement measurement){
        MeasurementDTO measurementDTO = modelMapper.map(measurement,MeasurementDTO.class);
        measurementDTO.setSensorDTO(convertToSensor(measurement.getSensor()));
        return measurementDTO;
    }
    private SensorDTO convertToSensor(Sensor sensor){
        return modelMapper.map(sensor,SensorDTO.class);
    }
}
