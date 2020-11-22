package com.example.dna.sofiamedical.service;

import com.example.dna.sofiamedical.dto.MedicalTestDto;
import com.example.dna.sofiamedical.dto.RedoMedicalTestDto;
import com.example.dna.sofiamedical.exception.DNAResultException;
import com.example.dna.sofiamedical.exception.MedicalTestNotFoundException;
import com.example.dna.sofiamedical.factory.MedicalTestFactory;
import com.example.dna.sofiamedical.model.MedicalTest;
import com.example.dna.sofiamedical.model.Patient;
import com.example.dna.sofiamedical.repository.MedicalTestRepository;
import com.example.dna.sofiamedical.validator.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class MedicalTestService implements TestService<MedicalTest, MedicalTestDto, RedoMedicalTestDto> {

    @Autowired
    private MedicalTestRepository medicalTestRepository;

    @Autowired
    private AlgorithmService algorithmService;

    @Autowired
    private MedicalTestFactory medicalTestFactory;

    @Autowired
    private Validator validator;

    @Override
    public List<MedicalTest> findAllTests() {
        return medicalTestRepository.findAll();
    }

    @Override
    public Page<MedicalTest> findTestByPatientName(String patientName, Pageable pageable) {
        return medicalTestRepository.findByPatientName(patientName, pageable);
    }

    @Override
    public Page<MedicalTest> findTestByPatientPhoneNumber(String phoneNumber, Pageable pageable) {
        return medicalTestRepository.findByPatientPhoneNumber(phoneNumber, pageable);
    }

    @Override
    public Double getTestResult(MedicalTestDto medicalTestDto) {

        validator.validate(medicalTestDto.getPatientDto());

        Double dnaResult = fetchDnaResultFromService(medicalTestDto.getPatientDto().getDnaCode());
        if (dnaResult == null) {
            throw new DNAResultException();
        }

        MedicalTest medicalTestToSave = createMedicalTest(medicalTestDto, dnaResult);
        saveTest(medicalTestToSave);

        return dnaResult;
    }

    @Transactional
    @Override
    public MedicalTest saveTest(MedicalTest medicalTest) {
        return medicalTestRepository.save(medicalTest);
    }

    @Override
    public Double redoMedicalTest(RedoMedicalTestDto object, String correlationId) {
        validator.validate(object.getPatientDto());

        MedicalTest medicalTest = medicalTestRepository
                .findByCorrelationId(correlationId)
                .orElseThrow(() -> new MedicalTestNotFoundException(correlationId));

        Double testResult = fetchDnaResultFromService(object.getPatientDto().getDnaCode());

        if (testResult == null) {
            throw new DNAResultException();
        }

        MedicalTest medicalTestToSave = updateMedicalTest(object, medicalTest, testResult);
        saveTest(medicalTestToSave);

        return testResult;
    }

    private Double fetchDnaResultFromService(String dna) {
        return algorithmService.getTestResult(dna);
    }

    private MedicalTest createMedicalTest(MedicalTestDto medicalTestDto, Double dnaResult) {
        return medicalTestFactory.createMedicalTest(medicalTestDto, dnaResult);
    }

    private MedicalTest updateMedicalTest(RedoMedicalTestDto redoMedicalTestDto, MedicalTest oldState, Double dnaResult) {
        return medicalTestFactory.updateMedicalTest(redoMedicalTestDto, oldState, dnaResult);
    }
}
