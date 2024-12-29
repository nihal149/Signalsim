package com.example.Signalslim.repository;


import com.example.Signalslim.model.PowerPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PowerPointRepository extends JpaRepository<PowerPoint, Long> {
    // Vous pouvez ajouter des méthodes spécifiques si nécessaire, par exemple :
    // List<PowerPoint> findByPowerValueGreaterThan(double value);
}
