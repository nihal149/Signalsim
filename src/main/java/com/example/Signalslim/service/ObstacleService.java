package com.example.Signalslim.service;

import com.example.Signalslim.model.Obstacle;
import com.example.Signalslim.model.Signal;
import com.example.Signalslim.repository.ObstacleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObstacleService {

    private final ObstacleRepository obstacleRepository;

    public ObstacleService(ObstacleRepository obstacleRepository) {
        this.obstacleRepository = obstacleRepository;
    }

    // Ajouter un nouvel obstacle
    public Obstacle addObstacle(Obstacle obstacle) {
        return obstacleRepository.save(obstacle);
    }

    // Supprimer un obstacle
    public void deleteObstacle(Long id) {
        obstacleRepository.deleteById(id);
    }

    // Récupérer tous les obstacles
    public List<Obstacle> getAllObstacles() {
        return obstacleRepository.findAll();
    }

    // Récupérer un obstacle par ID
    public Obstacle getObstacleById(Long id) {
        return obstacleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Obstacle not found with ID: " + id));
    }

    // Calculer l'impact d'un obstacle sur un signal
    public double calculateImpact(Obstacle obstacle, Signal signal) {
        double impact = obstacle.getHeight() * 0.1; // Facteur d'atténuation arbitraire
        return Math.max(0, signal.getSignalStrength() - impact);
    }
}