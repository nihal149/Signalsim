package com.example.Signalslim.service;

import com.example.Signalslim.dto.SignalDTO;  // Assurez-vous d'importer la classe SignalDTO correcte

import com.example.Signalslim.model.Signal;
import com.example.Signalslim.model.PowerPoint;
import com.example.Signalslim.model.SignalCriteria;
import com.example.Signalslim.repository.SignalRepository;
import com.example.Signalslim.repository.PowerPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SignalService {

    private final SignalRepository signalRepository;
    private final PowerPointRepository powerPointRepository;

    @Autowired
    public SignalService(SignalRepository signalRepository, PowerPointRepository powerPointRepository) {
        this.signalRepository = signalRepository;
        this.powerPointRepository = powerPointRepository;
    }


    // Méthode pour ajouter un signal
    public SignalDTO addSignal(Signal signal) {
        // Sauvegarder le signal dans la base de données
        Signal savedSignal = signalRepository.save(signal);
        // Retourner le Signal sous forme de DTO
        return savedSignal.toDTO();
    }
    // Méthode pour sauvegarder un PowerPoint
    public PowerPoint savePowerPoint(PowerPoint powerPoint) {
        return powerPointRepository.save(powerPoint);
    }

    // Méthode pour mettre à jour un signal
    public SignalDTO updateSignal(Long id, SignalDTO signalDTO) {
        // Récupérer le signal à mettre à jour
        Signal signal = signalRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Signal non trouvé"));

        // Mettre à jour les propriétés du signal
        signal.setSignalStrength(signalDTO.getSignalStrength());
        signal.setFrequency(signalDTO.getFrequency());

        // Mettre à jour le PowerPoint, si nécessaire
        if (signalDTO.getLatitude() != 0 && signalDTO.getLongitude() != 0) {
            PowerPoint powerPoint = signal.getPowerPoint();
            powerPoint.setxPosition(signalDTO.getLatitude());
            powerPoint.setyPosition(signalDTO.getLongitude());
            powerPointRepository.save(powerPoint);  // Sauvegarder le PowerPoint mis à jour
        }

        // Sauvegarder les modifications du signal
        return signalRepository.save(signal).toDTO();  // Retourner le SignalDTO mis à jour
    }
    // Méthode pour supprimer un signal
    public void deleteSignal(Long id) {
        if (!signalRepository.existsById(id)) {
            throw new IllegalArgumentException("Signal non trouvé");
        }
        signalRepository.deleteById(id);
    }

    // Méthode pour rechercher des signaux par critères
    public List<SignalDTO> findSignalsByCriteria(SignalCriteria criteria) {
        List<Signal> signals;

        // Recherche par fréquence
        if (criteria.getFrequency() != null) {
            signals = signalRepository.findByFrequency(criteria.getFrequency());
        }
        // Recherche par puissance
        else if (criteria.getSignalStrength() != null) {
            signals = signalRepository.findBySignalStrength(criteria.getSignalStrength());
        }
        // Autres critères peuvent être ajoutés ici
        else {
            signals = signalRepository.findAll();
        }

        // Conversion des entités Signal en SignalDTO
        return signals.stream()
                .map(Signal::toDTO)
                .toList();
    }
}
