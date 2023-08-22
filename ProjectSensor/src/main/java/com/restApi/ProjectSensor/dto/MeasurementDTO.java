package com.restApi.ProjectSensor.dto;

import com.restApi.ProjectSensor.models.Sensor;
import jakarta.validation.constraints.*;

public class MeasurementDTO {
    @NotNull(message = "Значение не должно быть пустым")
    @DecimalMin(value = "-100.0", inclusive = true, message = "Значение должно быть не меньше -100")
    @DecimalMax(value = "100.0", inclusive = true, message = "Значение должно быть не больше 100")
    private double value;
    @NotNull
    private boolean raining;
    private SensorDTO sensorDTO;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean getRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensorDTO() {
        return sensorDTO;
    }

    public void setSensorDTO(SensorDTO sensorDTO) {
        this.sensorDTO = sensorDTO;
    }
    @Override
    public String toString() {
        return "MeasurementDTO{\n" +
                "\tvalue: " + value +
                ",\n\training: " + raining +
                ",\n\tsensorDTO: " +"{"+
                "\n\t\tname: "+sensorDTO.getName() +
                "\n\t}"+
                "\n}";
    }
}
