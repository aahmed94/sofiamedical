package com.example.dna.sofiamedical.assembler;

import com.example.dna.sofiamedical.controller.MedicalTestController;
import com.example.dna.sofiamedical.model.MedicalTest;
import com.example.dna.sofiamedical.model.Patient;
import com.example.dna.sofiamedical.resource.MedicalTestResource;
import com.example.dna.sofiamedical.resource.PatientResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class MedicalTestAssembler extends RepresentationModelAssemblerSupport<MedicalTest, MedicalTestResource> {

    @Autowired
    private PatientAssembler patientAssembler;

    public MedicalTestAssembler() {
        super(MedicalTestController.class, MedicalTestResource.class);
    }

    @Override
    public MedicalTestResource toModel(MedicalTest medicalTest) {
        MedicalTestResource medicalTestResource = new MedicalTestResource();

        medicalTestResource.setPatientResource(createPatientResource(medicalTest.getPatient()));
        medicalTestResource.setTestResult(medicalTest.getTestResult());
        medicalTestResource.setTestDate(medicalTest.getTestDate());
        medicalTestResource.setSymptoms(medicalTest.getSymptoms());

        return medicalTestResource;
    }

    private PatientResource createPatientResource(Patient patient) {
        return patientAssembler.toModel(patient);
    }
}
