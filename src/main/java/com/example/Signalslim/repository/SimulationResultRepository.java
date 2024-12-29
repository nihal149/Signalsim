package com.example.Signalslim.repository;


import com.example.Signalslim.model.SimulationResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SimulationResultRepository extends JpaRepository<SimulationResult, Long> {
    // Vous pouvez ajouter des méthodes spécifiques si nécessaire, par exemple :
    List<SimulationResult> findByUserId(Long userId);
}
