package com.restApi.ProjectSensor.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Measurement")
public class Measurement {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "value")
    @NotNull(message = "Значение не должно быть пустым")
    @DecimalMin(value = "-100.0", inclusive = true, message = "Значение должно быть не меньше -100")
    @DecimalMax(value = "100.0", inclusive = true, message = "Значение должно быть не больше 100")
    private double value;
    @Column(name = "raining")
    @NotNull
    private boolean raining;
    @Column(name = "update_at")
    private LocalDateTime updateAt;
    @ManyToOne
    @JoinColumn(name = "sensor_id", referencedColumnName = "id")
    private Sensor sensor;
    public Measurement() {}
    public Measurement(double value, boolean raining, LocalDateTime updateAt, Sensor sensor) {
        this.value = value;
        this.raining = raining;
        this.updateAt = updateAt;
        this.sensor = sensor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }
}
