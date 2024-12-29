package com.example.Signalslim.service;

import com.example.Signalslim.model.ExportData;
import com.example.Signalslim.repository.ExportDataRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;  // Import nécessaire pour formater LocalDateTime
import java.time.LocalDateTime;  // Import nécessaire pour LocalDateTime
import java.util.List;

@Service
public class ExportService {

    @Autowired
    private ExportDataRepository exportDataRepository;

    // Méthode pour exporter les signaux sous format CSV
    public void exportSignalsToCSV(List<ExportData> exportDataList) throws IOException {
        String csvFileName = "signals_export_" + System.currentTimeMillis() + ".csv";
        try (FileWriter writer = new FileWriter(csvFileName)) {
            writer.append("ID,FileName,ExportDate,Status\n");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");  // Définir un format pour LocalDateTime

            for (ExportData data : exportDataList) {
                writer.append(data.getId().toString())
                        .append(",")
                        .append(data.getFileName())
                        .append(",")
                        .append(data.getExportDate().format(formatter))  // Formater le LocalDateTime
                        .append(",")
                        .append(data.getStatus())
                        .append("\n");
            }
        } catch (IOException e) {
            throw new IOException("Error while exporting signals to CSV", e);
        }
    }

    // Méthode pour exporter les simulations
    public void exportSimulations(List<ExportData> exportDataList) throws IOException {
        String csvFileName = "simulations_export_" + System.currentTimeMillis() + ".csv";
        try (FileWriter writer = new FileWriter(csvFileName)) {
            writer.append("SimulationID,Result\n");

            for (ExportData data : exportDataList) {
                writer.append(data.getId().toString())
                        .append(",")
                        .append(data.getFileName())  // Ici, vous pouvez remplacer par les résultats réels des simulations
                        .append("\n");
            }
        } catch (IOException e) {
            throw new IOException("Error while exporting simulations", e);
        }
    }

    // Méthode pour exporter les zones (par exemple au format JSON)
    public void exportZonesToJSON(List<ExportData> exportDataList) throws IOException {
        String jsonFileName = "zones_export_" + System.currentTimeMillis() + ".json";
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(jsonFileName), exportDataList); // Vous pourriez adapter les données selon le modèle des zones
        } catch (IOException e) {
            throw new IOException("Error while exporting zones to JSON", e);
        }
    }

    // Méthode pour enregistrer un ExportData après un export
    public void saveExportData(ExportData exportData) {
        exportDataRepository.save(exportData);
    }

    // Méthode pour préparer les données avant export
    public List<ExportData> prepareDataForExport() {
        // Exemple, ici vous pouvez récupérer les données selon un statut ou autre critère
        return exportDataRepository.findByStatus("PENDING");
    }
}
