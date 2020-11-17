package com.example.dna.sofiamedical.validator;

import com.example.dna.sofiamedical.dto.PatientDto;
import com.example.dna.sofiamedical.exception.IllegalEmailFormatException;
import com.example.dna.sofiamedical.exception.IllegalPatientPhoneNumberFormatException;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class PatientValidator implements Validator<PatientDto> {

    private static final String EXCEPTION_MESSAGE = "%s cannot be null or empty!";
    private static final Pattern PHONE_FORMAT_PATTERN = Pattern.compile("^[0-9\\+]{10,13}$");
    private static final EmailValidator emailValidator = EmailValidator.getInstance();

    @Override
    public void validate(PatientDto patientDto) {
        if (patientDto == null) {
            throw new NullPointerException("Patient cannot be null!");
        }

        if (patientDto.getName() == null || patientDto.getName().isEmpty()) {
            throw createIllegalArgumentException("Patient name");
        }

        if (patientDto.getDnaCode() == null || patientDto.getDnaCode().isEmpty()) {
            throw createIllegalArgumentException("Patient dna");
        }

        if (patientDto.getPhoneNumber() == null) {
            throw createIllegalArgumentException("Patient phone number");
        }

        if (patientDto.getEmail() != null && !isEmailValid(patientDto.getEmail())) {
            throw new IllegalEmailFormatException();
        }

        if (patientDto.getPhoneNumber() != null && !isPhoneNumberValid(patientDto.getPhoneNumber())) {
            throw new IllegalPatientPhoneNumberFormatException();
        }
    }

    private boolean isPhoneNumberValid(String phoneNumber) {
        Matcher matcher = PHONE_FORMAT_PATTERN.matcher(phoneNumber);
        return matcher.matches();
    }

    private boolean isEmailValid(String email) {
        return emailValidator.isValid(email);
    }

    private IllegalArgumentException createIllegalArgumentException(String nullField) {
       return new IllegalArgumentException(String.format(EXCEPTION_MESSAGE, nullField));
    }
}