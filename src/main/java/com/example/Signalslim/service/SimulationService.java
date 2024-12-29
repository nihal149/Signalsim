package com.example.Signalslim.service;

import com.example.Signalslim.dto.SimulationResultDTO;
import com.example.Signalslim.model.*;
import com.example.Signalslim.repository.SimulationResultRepository;
import com.example.Signalslim.repository.UserRepository;
import com.example.Signalslim.mapper.SimulationResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SimulationService {

    private final SimulationResultRepository simulationResultRepository;
    private final UserRepository userRepository;
    private final SimulationResultMapper simulationResultMapper;

    @Autowired
    public SimulationService(SimulationResultRepository simulationResultRepository,
                             UserRepository userRepository,
                             SimulationResultMapper simulationResultMapper) {
        this.simulationResultRepository = simulationResultRepository;
        this.userRepository = userRepository;
        this.simulationResultMapper = simulationResultMapper;
    }

    /**
     * Initialise une nouvelle simulation avec les paramètres de configuration.
     * @param signal Signal à utiliser pour la simulation.
     * @param zone Zone à utiliser pour la simulation.
     */
    public void initializeSimulation(Signal signal, Zone zone) {
        // Logique d'initialisation de la simulation avec Signal et Zone
        System.out.println("Initialisation de la simulation avec le signal : " + signal + " et la zone : " + zone);
        // Ajoutez ici votre logique d'initialisation de la simulation
    }


    /**
     * Exécute la simulation avec un ID de simulation.
     * @param simulationId L'ID de la simulation à exécuter.
     * @return SimulationResultDTO contenant les résultats.
     */
    public SimulationResultDTO runSimulation(Long simulationId) {
        // Vérifier si la simulation existe
        Optional<SimulationResult> simulationResultOpt = simulationResultRepository.findById(simulationId);
        if (simulationResultOpt.isEmpty()) {
            throw new IllegalArgumentException("Simulation avec l'ID " + simulationId + " non trouvée.");
        }

        SimulationResult simulationResult = simulationResultOpt.get();

        // Récupérer les objets associés à la simulation (Signal, Zone, Obstacle, PowerPoint)
        Signal signal = simulationResult.getSignal(); // Supposons que Signal est lié à SimulationResult
        Zone zone = simulationResult.getZone(); // Idem pour Zone
        Obstacle obstacle = simulationResult.getObstacle(); // Idem pour Obstacle
        PowerPoint powerPoint = simulationResult.getPowerPoint(); // Idem pour PowerPoint

        // Simuler le travail, calculer les résultats, etc.
        simulationResult.setResultData("Simulation terminée avec succès");
        simulationResult.setSimulationDate(new java.util.Date());

        // Sauvegarder le résultat de la simulation
        simulationResultRepository.save(simulationResult);

        // Mapper le résultat et le retourner sous forme de DTO
        return simulationResultMapper.toDTO(simulationResult);
    }

    /**
     * Récupère les résultats d'une simulation en fonction de son ID.
     * @param simulationId L'ID de la simulation.
     * @return SimulationResultDTO contenant les résultats.
     */
    public SimulationResultDTO getSimulationResults(Long simulationId) {
        Optional<SimulationResult> simulationResultOpt = simulationResultRepository.findById(simulationId);
        if (simulationResultOpt.isEmpty()) {
            throw new IllegalArgumentException("Simulation avec l'ID " + simulationId + " non trouvée.");
        }

        SimulationResult simulationResult = simulationResultOpt.get();
        return simulationResultMapper.toDTO(simulationResult);
    }

    /**
     * Stocke les résultats de la simulation dans la base de données.
     * @param resultDTO Le DTO contenant les résultats de la simulation.
     * @return L'entité SimulationResult stockée.
     */
    public SimulationResult storeSimulationResults(SimulationResultDTO resultDTO) {
        // Mapper le DTO en entité
        SimulationResult simulationResult = simulationResultMapper.toEntity(resultDTO);

        // Optionnellement, vous pouvez définir l'utilisateur qui a exécuté la simulation
        Optional<User> userOpt = userRepository.findById(resultDTO.getUserId());
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("Utilisateur introuvable avec l'ID: " + resultDTO.getUserId());
        }
        User user = userOpt.get();
        simulationResult.setUser(user);

        // Sauvegarder le résultat dans le repository
        return simulationResultRepository.save(simulationResult);
    }
}
