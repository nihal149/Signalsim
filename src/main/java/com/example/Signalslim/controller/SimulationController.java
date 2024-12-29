package com.example.Signalslim.controller;


import com.example.Signalslim.dto.SimulationResultDTO;
import com.example.Signalslim.model.Signal;
import com.example.Signalslim.model.SimulationConfig;
import com.example.Signalslim.model.Zone;
import com.example.Signalslim.service.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/simulation")
public class SimulationController {

    private final SimulationService simulationService;

    @Autowired
    public SimulationController(SimulationService simulationService) {
        this.simulationService = simulationService;
    }

    /**
     * Endpoint to initialize a new simulation with a given configuration.
     * @param config The configuration data for the simulation.
     * @return A response indicating the initialization status.
     */
    @PostMapping("/initialize")
    public ResponseEntity<String> initializeSimulation(@RequestBody SimulationConfig config) {
        try {
            Signal signal = config.getSignal(); // Extraction du signal
            Zone zone = config.getZone(); // Extraction de la zone
            simulationService.initializeSimulation(signal, zone); // Appel du service avec Signal et Zone
            return ResponseEntity.ok("Simulation initialized.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }


    /**
     * Endpoint to run a simulation based on the simulation ID.
     * @param simulationId The ID of the simulation to run.
     * @return A response containing the results of the simulation.
     */
    @PostMapping("/run/{simulationId}")
    public ResponseEntity<SimulationResultDTO> runSimulation(@PathVariable Long simulationId) {
        try {
            SimulationResultDTO resultDTO = simulationService.runSimulation(simulationId);
            return ResponseEntity.ok(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    /**
     * Endpoint to retrieve the results of a simulation by its ID.
     * @param simulationId The ID of the simulation to retrieve results for.
     * @return A response containing the results of the simulation.
     */
    @GetMapping("/results/{simulationId}")
    public ResponseEntity<SimulationResultDTO> getSimulationResults(@PathVariable Long simulationId) {
        try {
            SimulationResultDTO resultDTO = simulationService.getSimulationResults(simulationId);
            return ResponseEntity.ok(resultDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body(null);
        }
    }

    /**
     * Endpoint to store simulation results in the database.
     * @param resultDTO The DTO containing the simulation results to store.
     * @return A response indicating the success of the storage.
     */
    @PostMapping("/store")
    public ResponseEntity<String> storeSimulationResults(@RequestBody SimulationResultDTO resultDTO) {
        try {
            simulationService.storeSimulationResults(resultDTO);
            return ResponseEntity.ok("Simulation results stored successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error storing simulation results: " + e.getMessage());
        }
    }
}
