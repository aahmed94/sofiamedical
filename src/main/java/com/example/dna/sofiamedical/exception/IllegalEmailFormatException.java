package com.example.dna.sofiamedical.exception;

public class IllegalEmailFormatException extends IllegalArgumentException {
    private static final String EXCEPTION_MESSAGE = "Wrong patient email format!";

    public IllegalEmailFormatException() {
        super(EXCEPTION_MESSAGE);
    }
}
