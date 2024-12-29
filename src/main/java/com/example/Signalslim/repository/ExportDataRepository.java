package com.example.Signalslim.repository;

import com.example.Signalslim.model.ExportData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;  // Import nécessaire pour LocalDateTime
import java.util.List;

@Repository
public interface ExportDataRepository extends JpaRepository<ExportData, Long> {

    List<ExportData> findByStatus(String status);

    // Trouver tous les exports effectués après une certaine date
    List<ExportData> findByExportDateAfter(LocalDateTime exportDate);  // Utilisation de LocalDateTime
}
