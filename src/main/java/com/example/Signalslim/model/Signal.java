package com.example.Signalslim.model;

import jakarta.persistence.*;
import com.example.Signalslim.dto.SignalDTO;
@Entity
@Table(name = "signals")
public class Signal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double signalStrength;
    private double frequency;

    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;

    @ManyToOne
    @JoinColumn(name = "obstacle_id")
    private Obstacle obstacle;

    @ManyToOne
    @JoinColumn(name = "power_point_id")  // Ajoutez cette ligne pour établir la relation avec PowerPoint
    private PowerPoint powerPoint;  // Ajoutez le champ PowerPoint ici

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

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public PowerPoint getPowerPoint() {
        return powerPoint;  // Ajoutez le getter
    }

    public void setPowerPoint(PowerPoint powerPoint) {  // Ajoutez le setter
        this.powerPoint = powerPoint;
    }

    // Méthode pour convertir Signal en SignalDTO
    public SignalDTO toDTO() {
        // Utilisation des coordonnées du PowerPoint pour latitude et longitude
        return new SignalDTO(
                this.id,
                this.signalStrength,
                this.frequency,
                this.powerPoint.getxPosition(),  // Utilisation du getter de PowerPoint pour la latitude
                this.powerPoint.getyPosition()   // Utilisation du getter de PowerPoint pour la longitude
        );
    }

}
