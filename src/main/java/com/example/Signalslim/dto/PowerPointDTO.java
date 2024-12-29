package com.example.Signalslim.dto;


public class PowerPointDTO {

    private String id; // Identifiant unique pour le point de puissance
    private double powerValue; // Valeur de la puissance du point
    private double latitude; // Latitude du point de puissance
    private double longitude; // Longitude du point de puissance
    private double altitude; // Altitude du point (utile pour les calculs de propagation)
    private double coverageRadius; // Rayon de couverture du point

    // Constructeur
    public PowerPointDTO(String id, double powerValue, double latitude, double longitude, double altitude, double coverageRadius) {
        this.id = id;
        this.powerValue = powerValue;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.coverageRadius = coverageRadius;
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getPowerValue() {
        return powerValue;
    }

    public void setPowerValue(double powerValue) {
        this.powerValue = powerValue;
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

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public double getCoverageRadius() {
        return coverageRadius;
    }

    public void setCoverageRadius(double coverageRadius) {
        this.coverageRadius = coverageRadius;
    }
}
