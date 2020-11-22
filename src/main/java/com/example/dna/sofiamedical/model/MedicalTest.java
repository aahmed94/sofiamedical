package com.example.dna.sofiamedical.model;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.UUID;

@Entity
@Table(name = "medical_test")
public class MedicalTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "medical_test_id")
    private Long id;

    @Column(name = "correlation_id", updatable = false, nullable = false)
    private String correlationId;

    @PrePersist
    protected void onCreate() { correlationId = UUID.randomUUID().toString();}

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "symptom", nullable = false)
    private String symptoms;

    @Column(name = "test_result", nullable = false)
    private Double testResult;

    @Column(name = "test_date", nullable = false)
    private OffsetDateTime testDate;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
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

    public String getCorrelationId() {
        return correlationId;
    }
}
