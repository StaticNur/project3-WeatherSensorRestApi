package com.restApi.ProjectSensor.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Sensor")
public class Sensor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    @NotEmpty
    private String name;
    @OneToMany(mappedBy = "sensor",cascade = CascadeType.PERSIST)
    private List<Measurement> measurements;
    public Sensor() {}
    public Sensor(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return Objects.equals(name, sensor.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
