package com.example.Signalslim.mapper;

import com.example.Signalslim.dto.ExportDataDTO;
import com.example.Signalslim.model.ExportData;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;
@Mapper
public interface ExportDataMapper {

    ExportDataMapper INSTANCE = Mappers.getMapper(ExportDataMapper.class);

    // Mapping principal
    @Mapping(source = "id", target = "exportId")
    @Mapping(source = "dataField", target = "rows") // Conversion personnalisée simplifiée
    @Mapping(source = "exportDate", target = "date")
    @Mapping(source = "user.username", target = "userName")
    @Mapping(source = "status", target = "exportStatus")
    ExportDataDTO exportDataToDTO(ExportData exportData);

    @Mapping(source = "exportId", target = "id")
    @Mapping(source = "rows", target = "dataField") // Conversion personnalisée simplifiée
    @Mapping(source = "date", target = "exportDate")
    @Mapping(source = "userName", target = "user.username")
    @Mapping(source = "exportStatus", target = "status")
    ExportData dtoToExportData(ExportDataDTO exportDataDTO);

    // Conversion simplifiée entre String et List<List<Object>>
    default List<List<Object>> stringToRows(String dataField) {
        if (dataField == null || dataField.isEmpty()) {
            return new ArrayList<>(); // Retourner une liste vide si dataField est nul ou vide
        }
        List<List<Object>> rows = new ArrayList<>();
        rows.add(List.of(dataField)); // Ajouter dataField comme une seule ligne
        return rows;
    }

    default String rowsToString(List<List<Object>> rows) {
        if (rows == null || rows.isEmpty()) {
            return ""; // Retourner une chaîne vide si rows est nul ou vide
        }
        return rows.get(0).toString(); // Retourner la première ligne sous forme de chaîne
    }
}

