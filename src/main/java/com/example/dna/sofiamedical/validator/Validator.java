package com.example.dna.sofiamedical.validator;

public interface Validator<T> {
    /**
     * Validates the given object.
     * @param object The object to be validated.
     * @return void
     * If the method is executed without errors, all the fields are valid.
     * */
    void validate(T object);
}
