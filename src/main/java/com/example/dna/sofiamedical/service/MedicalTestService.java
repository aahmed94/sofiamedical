package com.example.dna.sofiamedical.service;

import com.example.dna.sofiamedical.dto.MedicalTestDto;
import com.example.dna.sofiamedical.exception.DNAResultException;
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

@Service
public class MedicalTestService implements TestService<MedicalTest, MedicalTestDto> {

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

    private Double fetchDnaResultFromService(String dna) {
        return algorithmService.getTestResult(dna);
    }

    private MedicalTest createMedicalTest(MedicalTestDto medicalTestDto, Double dnaResult) {
        return medicalTestFactory.createMedicalTest(medicalTestDto, dnaResult);
    }
}
