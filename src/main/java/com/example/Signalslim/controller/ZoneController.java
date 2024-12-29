package com.example.Signalslim.controller;


import com.example.Signalslim.dto.ZoneDTO;
import com.example.Signalslim.service.ZoneService;
import com.example.Signalslim.model.ZoneCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    private final ZoneService zoneService;

    @Autowired
    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    /**
     * Create a new Zone.
     * @param zoneDTO The DTO containing the information for the new Zone.
     * @return The created Zone as a DTO.
     */
    @PostMapping(produces = { "application/json", "application/xml" })
    public ResponseEntity<ZoneDTO> createZone(@RequestBody ZoneDTO zoneDTO) {
        ZoneDTO createdZone = zoneService.createZone(zoneDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdZone);  // Retourner la zone avec un statut 201
    }


    /**
     * Update an existing Zone by ID.
     * @param id The ID of the Zone to update.
     * @param zoneDTO The DTO containing the updated information.
     * @return The updated Zone as a DTO.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ZoneDTO> updateZone(@PathVariable Long id, @RequestBody ZoneDTO zoneDTO) {
        ZoneDTO updatedZone = zoneService.updateZone(id, zoneDTO);
        return updatedZone != null ? ResponseEntity.ok(updatedZone) : ResponseEntity.notFound().build();
    }



    /**
     * Delete a Zone by ID.
     * @param id The ID of the Zone to delete.
     * @return ResponseEntity indicating the result of the operation.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteZone(@PathVariable Long id) {
        return zoneService.deleteZone(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    /**
     * Retrieve Zones based on criteria.
     * @param criteria Search criteria for filtering zones.
     * @return List of Zones matching the criteria.
     */
    @PostMapping("/search")
    public ResponseEntity<List<ZoneDTO>> findZonesByCriteria(@RequestBody ZoneCriteria criteria) {
        List<ZoneDTO> zones = zoneService.findZonesByCriteria(criteria);
        return zones.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(zones);
    }

    /**
     * Validate a Zone before creating or updating it.
     * @param zoneDTO The Zone DTO to validate.
     * @return ResponseEntity indicating if the Zone is valid.
     */
    @PostMapping("/validate")
    public ResponseEntity<Boolean> validateZone(@RequestBody ZoneDTO zoneDTO) {
        return ResponseEntity.ok(zoneService.validateZone(zoneDTO));  // Retourne directement le résultat de la validation
    }
    /**
     * Retrieve all Zones.
     * @return A list of all Zones.
     */
    @GetMapping(produces = { "application/json", "application/xml" })
    public ResponseEntity<List<ZoneDTO>> getAllZones() {
        List<ZoneDTO> zones = zoneService.getAllZones();
        return zones.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(zones);
    }


}
