package com.restApi.ProjectSensor.util.errors;

import java.time.LocalDateTime;

public class SensorErrorResponse{
    private String message;
    private LocalDateTime localDateTime;

    public SensorErrorResponse(String message, LocalDateTime localDateTime) {
        this.message = message;
        this.localDateTime = localDateTime;
    }
    public String getMessage(){
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }
}
