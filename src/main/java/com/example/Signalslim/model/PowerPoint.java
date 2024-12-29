package com.example.Signalslim.model;

import jakarta.persistence.*;

@Entity
@Table(name = "power_points")
public class PowerPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "x_position", nullable = false)
    private double xPosition;

    @Column(name = "y_position", nullable = false)
    private double yPosition;

    @Column(name = "power_value", nullable = false)
    private double powerValue;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getxPosition() {
        return xPosition;
    }

    public void setxPosition(double xPosition) {
        this.xPosition = xPosition;
    }

    public double getyPosition() {
        return yPosition;
    }

    public void setyPosition(double yPosition) {
        this.yPosition = yPosition;
    }

    public double getPowerValue() {
        return powerValue;
    }

    public void setPowerValue(double powerValue) {
        this.powerValue = powerValue;
    }
}
