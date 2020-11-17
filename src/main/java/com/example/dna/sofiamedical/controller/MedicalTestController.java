package com.example.dna.sofiamedical.controller;

import com.example.dna.sofiamedical.assembler.MedicalTestAssembler;
import com.example.dna.sofiamedical.dto.MedicalTestDto;
import com.example.dna.sofiamedical.model.MedicalTest;
import com.example.dna.sofiamedical.resource.MedicalTestResource;
import com.example.dna.sofiamedical.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/medical-test", produces = MediaType.APPLICATION_JSON_VALUE)
public class MedicalTestController {

    @Autowired
    private TestService testService;

    @Autowired
    private MedicalTestAssembler medicalTestAssembler;

    @Autowired
    private PagedResourcesAssembler pagedResourcesAssembler;

    @GetMapping
    public ResponseEntity<CollectionModel<MedicalTestResource>> getAllPatientTests() {
        List<MedicalTest> medicalTestList = testService.findAllTests();

        return ResponseEntity.ok(medicalTestAssembler.toCollectionModel(medicalTestList));
    }

    @GetMapping(value = "/{patient-name}/get-by-name")
    public ResponseEntity<PagedModel<MedicalTestResource>> getMedicalTestByPatientName(@PathVariable(value = "patient-name") String patientName, Pageable pageable) {
        Page<MedicalTest> medicalTestPage = testService.findTestByPatientName(patientName, pageable);

        return ResponseEntity.ok(pagedResourcesAssembler.toModel(medicalTestPage, medicalTestAssembler));
    }

    @GetMapping(value = "/{phone-number}/get-by-number")
    public ResponseEntity<PagedModel<MedicalTestResource>> getMedicalTestByPatientPhoneNumber(@PathVariable(value = "phone-number") String phoneNumber, Pageable pageable) {
        Page<MedicalTest> medicalTestPage = testService.findTestByPatientPhoneNumber(phoneNumber, pageable);

        return ResponseEntity.ok(pagedResourcesAssembler.toModel(medicalTestPage, medicalTestAssembler));
    }

    @PostMapping(value = "/create")
    public ResponseEntity<Double> createMedicalTest(@RequestBody MedicalTestDto medicalTestDto) {
        Double testResult = testService.getTestResult(medicalTestDto);

        return ResponseEntity.ok(testResult);
    }
}