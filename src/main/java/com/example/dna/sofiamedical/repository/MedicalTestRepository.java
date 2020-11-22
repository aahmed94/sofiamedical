package com.example.dna.sofiamedical.repository;

import com.example.dna.sofiamedical.model.MedicalTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicalTestRepository extends JpaRepository<MedicalTest, Long> {
    Page<MedicalTest> findByPatientName(String patientName, Pageable pageable);
    Page<MedicalTest> findByPatientPhoneNumber(String phoneNumber, Pageable pageable);
    Optional<MedicalTest> findByCorrelationId(String correlationId);
}
