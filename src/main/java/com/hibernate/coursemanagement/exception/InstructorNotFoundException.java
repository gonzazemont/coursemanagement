package com.hibernate.coursemanagement.exception;

public class InstructorNotFoundException extends RuntimeException{

    public InstructorNotFoundException(Long id) {
        super("Instructor with ID " + id + " not found.");
    }

    public InstructorNotFoundException(String message) {
        super(message);
    }
}
