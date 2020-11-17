package com.example.dna.sofiamedical.exception;

public class DNAResultException extends NullPointerException {

    private static final String EXCEPTION_MESSAGE = "The DNA test result cannot be null. " +
            "Please check your dna sequence.";

    public DNAResultException() {
        super(EXCEPTION_MESSAGE);
    }
}
