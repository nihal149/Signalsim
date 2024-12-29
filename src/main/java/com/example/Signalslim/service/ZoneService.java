package com.example.Signalslim.service;


import com.example.Signalslim.dto.ZoneDTO;
import com.example.Signalslim.model.Zone;
import com.example.Signalslim.mapper.ZoneMapper;
import com.example.Signalslim.repository.ZoneRepository;
import com.example.Signalslim.model.ZoneCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ZoneService {

    private final ZoneRepository zoneRepository;
    private final ZoneMapper zoneMapper;

    @Autowired
    public ZoneService(ZoneRepository zoneRepository, ZoneMapper zoneMapper) {
        this.zoneRepository = zoneRepository;
        this.zoneMapper = zoneMapper;
    }

    /**
     * Create a new Zone.
     * @param zoneDTO The DTO containing the information for the new Zone.
     * @return The created Zone as a DTO.
     */
    public ZoneDTO createZone(ZoneDTO zoneDTO) {
        Zone zone = new Zone();
        zone.setName(zoneDTO.getName());
        zone.setLatitude(zoneDTO.getLatitude());
        zone.setLongitude(zoneDTO.getLongitude());
        zone.setType(zoneDTO.getType());

        // Sauvegarder la zone dans la base de données
        Zone savedZone = zoneRepository.save(zone);

        // Retourner la zone sauvegardée sous forme de DTO
        return new ZoneDTO(savedZone.getId(), savedZone.getName(), savedZone.getLatitude(), savedZone.getLongitude(), savedZone.getType());
    }

    /**
     * Update an existing Zone by ID.
     * @param id The ID of the Zone to update.
     * @param zoneDTO The DTO containing the updated information.
     * @return The updated Zone as a DTO.
     */
    public ZoneDTO updateZone(Long id, ZoneDTO zoneDTO) {
        return zoneRepository.findById(id)
                .map(zone -> {
                    zone.setName(zoneDTO.getName());
                    zone.setLatitude(zoneDTO.getLatitude());
                    zone.setLongitude(zoneDTO.getLongitude());
                    zone.setType(zoneDTO.getType());
                    return zoneMapper.toDTO(zoneRepository.save(zone));
                })
                .orElse(null);  // Retourne null si la zone n'existe pas
    }

    /**
     * Delete a Zone by ID.
     * @param id The ID of the Zone to delete.
     */
    public boolean deleteZone(Long id) {
        if (zoneRepository.existsById(id)) {
            zoneRepository.deleteById(id);
            return true;
        }
        return false;
    }



    public List<ZoneDTO> findZonesByCriteria(ZoneCriteria criteria) {
        return zoneRepository.findByCriteria(criteria).stream()
                .map(zoneMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Validate a Zone based on the provided DTO.
     * @param zoneDTO The Zone DTO to validate.
     * @return true if the zone is valid, false otherwise.
     */
    public boolean validateZone(ZoneDTO zoneDTO) {
        // Validation simplifiée : vérifier que tous les champs nécessaires ne sont pas nuls ou vides
        return zoneDTO.getName() != null && !zoneDTO.getName().isEmpty() &&
                zoneDTO.getLatitude() != 0 && zoneDTO.getLongitude() != 0 &&
                zoneDTO.getType() != null && !zoneDTO.getType().isEmpty();
    }
    /**
     * Retrieve all Zones.
     * @return A list of all Zones as DTOs.
     */
    public List<ZoneDTO> getAllZones() {
        List<Zone> zones = zoneRepository.findAll();
        return zones.stream()
                .map(zoneMapper::toDTO)
                .collect(Collectors.toList());
    }


}
