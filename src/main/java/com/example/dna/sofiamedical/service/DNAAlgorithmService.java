package com.example.dna.sofiamedical.service;

import com.example.dna.model.disorderprobability.DNA;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DNAAlgorithmService implements AlgorithmService {

    @Value("${dnaservice.url}")
    private String dnaAlgorithmUrl;

    @Override
    public Double getTestResult(String dna) {
        DNA dnaDTO = new DNA();
        dnaDTO.setCode(dna);

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<DNA> request = new HttpEntity<>(dnaDTO);

        Double geneticDisorderProbability = restTemplate.postForObject(dnaAlgorithmUrl, request, Double.class);

        return geneticDisorderProbability;
    }
}
