package com.example.dna.sofiamedical.resource;

import org.springframework.hateoas.RepresentationModel;
import java.time.OffsetDateTime;

public class MedicalTestResource extends RepresentationModel<MedicalTestResource> {
    private PatientResource patientResource;
    private Double testResult;
    private OffsetDateTime testDate;

    public PatientResource getPatientResource() {
        return patientResource;
    }

    public void setPatientResource(PatientResource patientResource) {
        this.patientResource = patientResource;
    }

    public Double getTestResult() {
        return testResult;
    }

    public void setTestResult(Double testResult) {
        this.testResult = testResult;
    }

    public OffsetDateTime getTestDate() {
        return testDate;
    }

    public void setTestDate(OffsetDateTime testDate) {
        this.testDate = testDate;
    }
}
