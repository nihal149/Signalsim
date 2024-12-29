package com.example.Signalslim.repository;


import com.example.Signalslim.model.Obstacle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObstacleRepository extends JpaRepository<Obstacle, Long> {
    // Vous pouvez ajouter des méthodes spécifiques si nécessaire, par exemple :
    // List<Obstacle> findByLocation(String location);
}
