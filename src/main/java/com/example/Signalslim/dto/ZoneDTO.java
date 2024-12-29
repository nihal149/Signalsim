package com.example.Signalslim.dto;

public class ZoneDTO {

    private Long id; // Identifiant unique de la zone
    private String name; // Nom de la zone
    private double latitude; // Latitude du centre de la zone
    private double longitude; // Longitude du centre de la zone
    private String type; // Type de la zone (Rural, Ruban, Intérieur)

    // Constructeur
    public ZoneDTO(Long id, String name, double latitude, double longitude, String type) {
        this.id = id;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
