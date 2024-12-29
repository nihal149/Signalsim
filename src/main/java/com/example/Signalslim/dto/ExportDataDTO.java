package com.example.Signalslim.dto;

import java.util.List;

public class ExportDataDTO {

    private String exportId;
    private List<List<Object>> rows;
    private String date;
    private String userName;
    private String exportStatus;

    // Constructeur avec tous les paramètres nécessaires
    public ExportDataDTO(String exportId, String date, List<String> headers, List<List<Object>> rows, String exportStatus) {
        this.exportId = exportId;
        this.date = date;
        this.rows = rows;
        this.exportStatus = exportStatus;

        // Vous pouvez définir userName ici si vous avez un moyen de le récupérer
        this.userName = "userNameExample";  // Remplacez par la logique réelle pour obtenir l'utilisateur
    }

    // Getters et Setters
    public String getExportId() {
        return exportId;
    }

    public void setExportId(String exportId) {
        this.exportId = exportId;
    }

    public List<List<Object>> getRows() {
        return rows;
    }

    public void setRows(List<List<Object>> rows) {
        this.rows = rows;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getExportStatus() {
        return exportStatus;
    }

    public void setExportStatus(String exportStatus) {
        this.exportStatus = exportStatus;
    }
}

