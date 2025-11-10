package com.hibernate.coursemanagement.exception;

public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(Long id) {
        super("Course with ID " + id + " not found.");
    }
    public CourseNotFoundException(String message) {
        super(message);
    }
}
