package com.example.dna.sofiamedical.exception;

public class IllegalPatientPhoneNumberFormatException extends NumberFormatException {
    private static final String EXCEPTION_MESSAGE = "Wrong patient phone number format!";

    public IllegalPatientPhoneNumberFormatException() {
        super(EXCEPTION_MESSAGE);
    }
}
