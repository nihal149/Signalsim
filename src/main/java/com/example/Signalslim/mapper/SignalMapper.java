package com.example.Signalslim.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.example.Signalslim.dto.SignalDTO;
import com.example.Signalslim.model.Signal;
import com.example.Signalslim.model.Zone;
import com.example.Signalslim.model.Obstacle;
import java.util.List;

@Mapper

public interface SignalMapper {

    SignalMapper INSTANCE = Mappers.getMapper(SignalMapper.class);

    // From Entity to DTO
    @Mapping(source = "id", target = "id")  // Correspondance directe
    @Mapping(source = "signalStrength", target = "signalStrength")  // Correct mapping
    @Mapping(source = "frequency", target = "frequency")
    @Mapping(source = "powerPoint.xPosition", target = "latitude") // Mappage correct pour latitude
    @Mapping(source = "powerPoint.yPosition", target = "longitude") // Mappage correct pour longitude
    SignalDTO signalToDTO(Signal signal);

    // From DTO to Entity
    @Mapping(source = "id", target = "id")
    @Mapping(source = "signalStrength", target = "signalStrength")  // Correct mapping
    @Mapping(source = "frequency", target = "frequency")
    @Mapping(source = "latitude", target = "powerPoint.xPosition")  // Mapping inverse pour latitude
    @Mapping(source = "longitude", target = "powerPoint.yPosition")  // Mapping inverse pour longitude
    Signal dtoToSignal(SignalDTO signalDTO);

    // From Entity List to DTO List
    List<SignalDTO> signalListToDTOList(List<Signal> signals);

    // From DTO List to Entity List
    List<Signal> dtoListToSignalList(List<SignalDTO> signalDTOs);

    // Méthode pour transformer Zone en longitude
    default double mapZoneLongitude(Zone zone) {
        return zone != null ? zone.getLongitude() : 0.0;
    }

    // Méthode pour transformer Obstacle en type
    default String mapObstacleType(Obstacle obstacle) {
        return obstacle != null ? obstacle.getType() : null;
    }
}
