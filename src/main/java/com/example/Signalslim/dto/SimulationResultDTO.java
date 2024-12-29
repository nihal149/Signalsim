package com.example.Signalslim.dto;

import java.util.Date;

public class SimulationResultDTO {

    private Long id;
    private String resultData;
    private Date simulationDate;
    private Long userId; // Identifiant de l'utilisateur associé à la simulation
    private String userName; // Nom de l'utilisateur associé à la simulation

    // Constructeur
    public SimulationResultDTO(Long id, String resultData, Date simulationDate, Long userId, String userName) {
        this.id = id;
        this.resultData = resultData;
        this.simulationDate = simulationDate;
        this.userId = userId;
        this.userName = userName;
    }

    // Getters et Setters
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
