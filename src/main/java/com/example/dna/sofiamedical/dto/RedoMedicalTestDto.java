package com.example.dna.sofiamedical.dto;

public class RedoMedicalTestDto extends MedicalTestDto {
    private String correlationId;

    public String getCorrelationId() {
        return correlationId;
    }

    public void setCorrelationId(String correlationId) {
        this.correlationId = correlationId;
    }
}
