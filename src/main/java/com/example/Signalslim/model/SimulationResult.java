package com.example.Signalslim.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "simulation_results")
public class SimulationResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "result_data", nullable = false)
    private String resultData;

    @Column(name = "simulation_date", nullable = false)
    private Date simulationDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "signal_id", nullable = false)
    private Signal signal;

    @ManyToOne
    @JoinColumn(name = "zone_id", nullable = false)
    private Zone zone;

    @ManyToOne
    @JoinColumn(name = "obstacle_id", nullable = true)
    private Obstacle obstacle;

    @ManyToOne
    @JoinColumn(name = "power_point_id", nullable = true)
    private PowerPoint powerPoint;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResultData() {
        return resultData;
    }

    public void setResultData(String resultData) {
        this.resultData = resultData;
    }

    public Date getSimulationDate() {
        return simulationDate;
    }

    public void setSimulationDate(Date simulationDate) {
        this.simulationDate = simulationDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Signal getSignal() {
        return signal;
    }

    public void setSignal(Signal signal) {
        this.signal = signal;
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
        return powerPoint;
    }

    public void setPowerPoint(PowerPoint powerPoint) {
        this.powerPoint = powerPoint;
    }
}
