package com.example.dna.sofiamedical.factory;

import com.example.dna.sofiamedical.dto.MedicalTestDto;
import com.example.dna.sofiamedical.dto.PatientDto;
import com.example.dna.sofiamedical.dto.RedoMedicalTestDto;
import com.example.dna.sofiamedical.model.MedicalTest;
import com.example.dna.sofiamedical.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@Component
public class MedicalTestFactory {

    @Autowired
    private PatientFactory patientFactory;

    public MedicalTest createMedicalTest(MedicalTestDto medicalTestDto, Double testResult) {
        MedicalTest medicalTest = new MedicalTest();

        medicalTest.setSymptoms(medicalTestDto.getSymptoms());
        medicalTest.setTestResult(testResult);
        medicalTest.setTestDate(OffsetDateTime.now());
        medicalTest.setPatient(createPatient(medicalTestDto.getPatientDto(), medicalTest));

        return medicalTest;
    }

    public MedicalTest updateMedicalTest(RedoMedicalTestDto redoMedicalTestDto, MedicalTest medicalTest, Double testResult) {
        medicalTest.setSymptoms(medicalTest.getSymptoms());
        medicalTest.setTestResult(testResult);
        medicalTest.setTestDate(OffsetDateTime.now());
        medicalTest.setPatient(createPatient(redoMedicalTestDto.getPatientDto(), medicalTest));

        return medicalTest;
    }

    private Patient createPatient(PatientDto patientDto, MedicalTest medicalTest) {
        Patient patient = patientFactory.createPatient(patientDto);

        if (patient.getMedicalTests() == null) {
            patient.setMedicalTests(new ArrayList<>());
        }

        patient.getMedicalTests().add(medicalTest);

        return patient;
    }
}
