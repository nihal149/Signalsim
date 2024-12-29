package com.example.Signalslim.controller;

import com.example.Signalslim.model.Obstacle;
import com.example.Signalslim.model.Signal;
import com.example.Signalslim.service.ObstacleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/obstacles")
public class ObstacleController {

    private final ObstacleService obstacleService;

    public ObstacleController(ObstacleService obstacleService) {
        this.obstacleService = obstacleService;
    }

    // Endpoint pour ajouter un obstacle
    @PostMapping
    public ResponseEntity<Obstacle> addObstacle(@RequestBody Obstacle obstacle) {
        Obstacle savedObstacle = obstacleService.addObstacle(obstacle);
        return ResponseEntity.ok(savedObstacle);
    }

    // Endpoint pour supprimer un obstacle
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObstacle(@PathVariable Long id) {
        obstacleService.deleteObstacle(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint pour récupérer tous les obstacles
    @GetMapping
    public ResponseEntity<List<Obstacle>> getAllObstacles() {
        List<Obstacle> obstacles = obstacleService.getAllObstacles();
        return ResponseEntity.ok(obstacles);
    }

    // Endpoint pour récupérer un obstacle par ID
    @GetMapping("/{id}")
    public ResponseEntity<Obstacle> getObstacleById(@PathVariable Long id) {
        Obstacle obstacle = obstacleService.getObstacleById(id);
        return ResponseEntity.ok(obstacle);
    }

    // Endpoint pour calculer l'impact d'un obstacle sur un signal
    @PostMapping("/{id}/impact")
    public ResponseEntity<Double> calculateImpact(
            @PathVariable Long id,
            @RequestBody Signal signal) {
        Obstacle obstacle = obstacleService.getObstacleById(id);
        double impact = obstacleService.calculateImpact(obstacle, signal);
        return ResponseEntity.ok(impact);
    }
}