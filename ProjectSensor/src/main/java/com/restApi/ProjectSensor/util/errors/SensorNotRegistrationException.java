package com.restApi.ProjectSensor.util.errors;

public class SensorNotRegistrationException extends RuntimeException{
    public SensorNotRegistrationException(String message){
        super(message);
    }
}
