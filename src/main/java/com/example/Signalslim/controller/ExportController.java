package com.example.Signalslim.controller;

import com.example.Signalslim.dto.ExportDataDTO;
import com.example.Signalslim.model.ExportData;
import com.example.Signalslim.service.ExportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/export")
public class ExportController {

    @Autowired
    private ExportService exportService;

    // Méthode pour l'exportation des signaux
    @GetMapping("/signals")
    public ResponseEntity<String> exportSignals() {
        try {
            // Préparer les données pour l'exportation
            List<ExportData> data = exportService.prepareDataForExport();

            // Exporter les signaux en CSV
            exportService.exportSignalsToCSV(data);

            // Créer un ExportDataDTO pour enregistrer l'export
            ExportDataDTO exportDataDTO = new ExportDataDTO(
                    "signals_" + System.currentTimeMillis(),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()),  // Format de la date
                    List.of("ID", "FileName", "ExportDate", "Status"),
                    List.of(
                            List.of(data.get(0).getId(), data.get(0).getFileName(), data.get(0).getExportDate(), data.get(0).getStatus())
                    ),
                    "CSV"
            );

            // Sauvegarder l'export dans la base de données
            ExportData exportData = new ExportData();
            exportData.setFileName("signals_export_" + System.currentTimeMillis() + ".csv");

            // Convertir la date actuelle en LocalDateTime et la formater selon le modèle attendu
            String formattedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            exportData.setExportDate(LocalDateTime.parse(formattedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            exportData.setStatus("SUCCESS");
            exportService.saveExportData(exportData);

            return ResponseEntity.status(HttpStatus.OK).body("Exportation des signaux réussie.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'exportation des signaux.");
        }
    }

    // Méthode pour l'exportation des simulations
    @GetMapping("/simulations")
    public ResponseEntity<String> exportSimulations() {
        try {
            // Préparer les données pour l'exportation des simulations
            List<ExportData> data = exportService.prepareDataForExport();

            // Exporter les simulations en CSV
            exportService.exportSimulations(data);

            // Créer un ExportDataDTO pour enregistrer l'export
            ExportDataDTO exportDataDTO = new ExportDataDTO(
                    "simulations_" + System.currentTimeMillis(),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()),  // Format de la date
                    List.of("ID", "FileName", "ExportDate", "Status"),
                    List.of(
                            List.of(data.get(0).getId(), data.get(0).getFileName(), data.get(0).getExportDate(), data.get(0).getStatus())
                    ),
                    "CSV"
            );

            // Sauvegarder l'export dans la base de données
            ExportData exportData = new ExportData();
            exportData.setFileName("simulations_export_" + System.currentTimeMillis() + ".csv");

            // Convertir la date actuelle en LocalDateTime et la formater selon le modèle attendu
            String formattedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            exportData.setExportDate(LocalDateTime.parse(formattedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            exportData.setStatus("SUCCESS");
            exportService.saveExportData(exportData);

            return ResponseEntity.status(HttpStatus.OK).body("Exportation des simulations réussie.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'exportation des simulations.");
        }
    }

    // Méthode pour l'exportation des zones au format JSON
    @GetMapping("/zones")
    public ResponseEntity<String> exportZones() {
        try {
            // Préparer les données pour l'exportation des zones
            List<ExportData> data = exportService.prepareDataForExport();

            // Exporter les zones en JSON
            exportService.exportZonesToJSON(data);

            // Créer un ExportDataDTO pour enregistrer l'export
            ExportDataDTO exportDataDTO = new ExportDataDTO(
                    "zones_" + System.currentTimeMillis(),
                    new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()),  // Format de la date
                    List.of("ID", "FileName", "ExportDate", "Status"),
                    List.of(
                            List.of(data.get(0).getId(), data.get(0).getFileName(), data.get(0).getExportDate(), data.get(0).getStatus())
                    ),
                    "JSON"
            );

            // Sauvegarder l'export dans la base de données
            ExportData exportData = new ExportData();
            exportData.setFileName("zones_export_" + System.currentTimeMillis() + ".json");

            // Convertir la date actuelle en LocalDateTime et la formater selon le modèle attendu
            String formattedDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            exportData.setExportDate(LocalDateTime.parse(formattedDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

            exportData.setStatus("SUCCESS");
            exportService.saveExportData(exportData);

            return ResponseEntity.status(HttpStatus.OK).body("Exportation des zones réussie.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'exportation des zones.");
        }
    }
}

