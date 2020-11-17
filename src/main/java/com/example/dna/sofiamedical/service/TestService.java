package com.example.dna.sofiamedical.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TestService<T, D> {
    List<T> findAllTests();
    Page<T> findTestByPatientName(String patientName, Pageable pageable);
    Page<T> findTestByPatientPhoneNumber(String phoneNumber, Pageable pageable);
    Double getTestResult(D object);
    T saveTest(T objectToSave);
}
