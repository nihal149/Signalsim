package com.example.Signalslim.dto;



public class ObstacleDTO {

    private String id; // Identifiant unique de l'obstacle
    private String type; // Type d'obstacle (p. ex. "bâtiment", "colline")
    private String description; // Description de l'obstacle
    private double latitude; // Latitude de l'obstacle
    private double longitude; // Longitude de l'obstacle
    private double height; // Hauteur de l'obstacle (utile pour les simulations)

    // Constructeur
    public ObstacleDTO(String id, String type, String description, double latitude, double longitude, double height) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
        this.height = height;
    }

    // Getters et Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
