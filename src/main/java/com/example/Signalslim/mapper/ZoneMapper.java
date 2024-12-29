package com.example.Signalslim.mapper;

import com.example.Signalslim.dto.ZoneDTO;
import com.example.Signalslim.model.Zone;
import org.springframework.stereotype.Component;

@Component
public class ZoneMapper {

    // Convertir Zone en ZoneDTO
    public ZoneDTO toDTO(Zone zone) {
        if (zone == null) {
            return null;
        }

        return new ZoneDTO(
                zone.getId(),
                zone.getName(),
                zone.getLatitude(),
                zone.getLongitude(),
                zone.getType()  // On ne mappe plus le radius
        );
    }

    // Convertir ZoneDTO en Zone
    public Zone toEntity(ZoneDTO zoneDTO) {
        if (zoneDTO == null) {
            return null;
        }

        Zone zone = new Zone();
        zone.setId(zoneDTO.getId());
        zone.setName(zoneDTO.getName());
        zone.setLatitude(zoneDTO.getLatitude());
        zone.setLongitude(zoneDTO.getLongitude());
        zone.setType(zoneDTO.getType());  // On ne prend plus en compte le radius

        return zone;
    }
}
