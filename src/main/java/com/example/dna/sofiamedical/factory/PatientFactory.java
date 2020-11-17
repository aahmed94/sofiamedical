package com.example.dna.sofiamedical.factory;

import com.example.dna.sofiamedical.dto.PatientDto;
import com.example.dna.sofiamedical.model.Patient;
import org.springframework.stereotype.Component;

@Component
public class PatientFactory {

    public Patient createPatient(PatientDto patientDto) {
        Patient patient = new Patient();

        patient.setName(patientDto.getName());
        patient.setDna(patientDto.getDnaCode());
        patient.setAge(patientDto.getAge());
        patient.setEmail(patientDto.getEmail());
        patient.setPhoneNumber(patientDto.getPhoneNumber());

        return patient;
    }
}
