package com.example.Signalslim.model;

import jakarta.persistence.*;

@Entity
@Table(name = "obstacles")
public class Obstacle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "height", nullable = false)
    private double height;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "type", nullable = false)  // Nouveau champ pour le type
    private String type; // Ajout du champ 'type' pour l'obstacle

    // Getters and Setters
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

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;  // Ajout du getter pour 'type'
    }

    public void setType(String type) {
        this.type = type;  // Ajout du setter pour 'type'
    }
}