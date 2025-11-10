package com.hibernate.coursemanagement.exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException(String email)
    {
        super("An instructor with email " + email + " already exists.");
    }
}
