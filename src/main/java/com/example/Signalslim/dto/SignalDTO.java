package com.example.Signalslim.dto;
public class SignalDTO {

    private Long id;
    private double signalStrength;  // Changer de String à double
    private double frequency;       // Changer de String à double
    private double latitude;        // Latitude (xPosition du PowerPoint)
    private double longitude;


    // Constructeur
    public SignalDTO(Long id, double signalStrength, double frequency, double latitude, double longitude) {
        this.id = id;
        this.signalStrength = signalStrength;
        this.frequency = frequency;
        this.latitude = latitude;
        this.longitude = longitude;

    }


    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getSignalStrength() {
        return signalStrength;
    }

    public void setSignalStrength(double signalStrength) {
        this.signalStrength = signalStrength;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}