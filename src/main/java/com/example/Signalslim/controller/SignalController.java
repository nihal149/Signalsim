package com.example.Signalslim.controller;

import com.example.Signalslim.dto.SignalDTO;
import com.example.Signalslim.model.PowerPoint;
import com.example.Signalslim.model.Signal;
import com.example.Signalslim.model.SignalCriteria;
import com.example.Signalslim.repository.SignalRepository;
import com.example.Signalslim.service.SignalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/signals")
public class SignalController {

    private final SignalService signalService;
    @Autowired
    private SignalRepository signalRepository;

    @Autowired
    public SignalController(SignalService signalService) {
        this.signalService = signalService;
    }

    // Méthode de création d'un signal
    @PostMapping(produces = {"application/json", "application/xml"})
    public ResponseEntity<SignalDTO> createSignal(@RequestBody SignalDTO signalDTO) {
        try {
            // Créer un PowerPoint et le sauvegarder dans la base de données
            PowerPoint powerPoint = new PowerPoint();
            powerPoint.setxPosition(signalDTO.getLatitude());
            powerPoint.setyPosition(signalDTO.getLongitude());

            // Sauvegarder le PowerPoint avant de l'ajouter au Signal
            powerPoint = signalService.savePowerPoint(powerPoint);

            // Créer un signal avec le PowerPoint
            Signal signal = new Signal();
            signal.setSignalStrength(signalDTO.getSignalStrength());
            signal.setFrequency(signalDTO.getFrequency());
            signal.setPowerPoint(powerPoint);  // Associe le PowerPoint au Signal

            // Ajouter le signal via le service
            SignalDTO createdSignal = signalService.addSignal(signal);

            return new ResponseEntity<>(createdSignal, HttpStatus.CREATED);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Méthode de mise à jour d'un signal
    @PutMapping(value = "/{id}", produces = {"application/json", "application/xml"})
    public ResponseEntity<SignalDTO> updateSignal(@PathVariable Long id, @RequestBody SignalDTO signalDTO) {
        try {
            SignalDTO updatedSignal = signalService.updateSignal(id, signalDTO);
            return ResponseEntity.ok(updatedSignal);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    // Méthode de suppression d'un signal
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSignal(@PathVariable Long id) {
        try {
            signalService.deleteSignal(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IllegalArgumentException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Méthode pour récupérer les signaux selon des critères
    @GetMapping
    public ResponseEntity<List<SignalDTO>> getSignals(@RequestParam(required = false) Double frequency,
                                                      @RequestParam(required = false) Double signalStrength) {
        SignalCriteria criteria = new SignalCriteria();
        if (frequency != null) criteria.setFrequency(frequency);
        if (signalStrength != null) criteria.setSignalStrength(signalStrength);

        List<SignalDTO> signals = signalService.findSignalsByCriteria(criteria);
        return new ResponseEntity<>(signals, HttpStatus.OK);
    }

    // Méthode pour calculer la longueur d'onde d'un signal
    @GetMapping("/wavelength/{id}")
    public ResponseEntity<Double> calculateWavelengthById(@PathVariable Long id) {
        // Récupérer le signal par ID
        Signal signal = signalRepository.findById(id).orElse(null);

        if (signal == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Signal non trouvé
        }

        // Calcul de la longueur d'onde en utilisant la fréquence du signal
        double wavelength = 3e8 / signal.getFrequency(); // vitesse de la lumière / fréquence

        return new ResponseEntity<>(wavelength, HttpStatus.OK);
    }

}
