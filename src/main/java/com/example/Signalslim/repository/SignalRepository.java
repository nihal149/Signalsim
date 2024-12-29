package com.example.Signalslim.repository;

import com.example.Signalslim.model.Signal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SignalRepository extends JpaRepository<Signal, Long> {

    // Trouver des signaux par fréquence
    List<Signal> findByFrequency(double frequency);

    // Trouver des signaux par puissance du signal
    List<Signal> findBySignalStrength(double signalStrength);

    // Trouver un signal par ID (retourne un Optional<Signal>)
    Optional<Signal> findById(Long id);
}
