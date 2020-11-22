package com.example.dna.sofiamedical.exception;

public class MedicalTestNotFoundException extends NullPointerException {
    private static final String EXCEPTION_MESSAGE = "Medical test with correlationId %s is not found";

    public MedicalTestNotFoundException(String correlationId) {
        super(String.format(EXCEPTION_MESSAGE, correlationId));
    }
}
