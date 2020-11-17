package com.example.dna.sofiamedical.assembler;

import com.example.dna.sofiamedical.controller.MedicalTestController;
import com.example.dna.sofiamedical.model.Patient;
import com.example.dna.sofiamedical.resource.PatientResource;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PatientAssembler extends RepresentationModelAssemblerSupport<Patient, PatientResource> {

    public PatientAssembler() {
        super(MedicalTestController.class, PatientResource.class);
    }

    @Override
    public PatientResource toModel(Patient patient) {
        PatientResource patientResource = new PatientResource();

        patientResource.setName(patient.getName());
        patientResource.setAge(patient.getAge());
        patientResource.setDna(patient.getDna());
        patientResource.setEmail(patient.getEmail());
        patientResource.setPhoneNumber(patient.getPhoneNumber());

        return patientResource;
    }
}
