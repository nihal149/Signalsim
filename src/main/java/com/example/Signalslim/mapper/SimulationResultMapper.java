package com.example.Signalslim.mapper;

import com.example.Signalslim.dto.SimulationResultDTO;
import com.example.Signalslim.model.SimulationResult;
import com.example.Signalslim.model.User;
import org.springframework.stereotype.Component;

@Component
public class SimulationResultMapper {

    // Convertir SimulationResult en SimulationResultDTO
    public SimulationResultDTO toDTO(SimulationResult simulationResult) {
        if (simulationResult == null) {
            return null;
        }

        User user = simulationResult.getUser(); // Récupérer l'utilisateur associé à la simulation

        return new SimulationResultDTO(
                simulationResult.getId(),
                simulationResult.getResultData(),
                simulationResult.getSimulationDate(),
                user != null ? user.getId() : null, // En cas d'utilisateur null
                user != null ? user.getUsername() : null // Utiliser getUsername au lieu de getUserName
        );
    }

    // Convertir SimulationResultDTO en SimulationResult
    public SimulationResult toEntity(SimulationResultDTO simulationResultDTO) {
        if (simulationResultDTO == null) {
            return null;
        }

        SimulationResult simulationResult = new SimulationResult();
        simulationResult.setId(simulationResultDTO.getId());
        simulationResult.setResultData(simulationResultDTO.getResultData());
        simulationResult.setSimulationDate(simulationResultDTO.getSimulationDate());

        // Remplir l'utilisateur (s'il est présent dans le DTO)
        User user = new User();
        user.setId(simulationResultDTO.getUserId());
        user.setUsername(simulationResultDTO.getUserName()); // Utiliser setUsername
        simulationResult.setUser(user);

        return simulationResult;
    }
}


