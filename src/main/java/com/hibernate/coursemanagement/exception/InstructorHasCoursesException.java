package com.hibernate.coursemanagement.exception;

public class InstructorHasCoursesException extends RuntimeException {

    public InstructorHasCoursesException( Long instructorId, int courseCount ) {
        super( "Instructor with ID " + instructorId + " cannot be deleted because they are assigned to " + courseCount + " course(s). Please reassign or delete the courses before deleting the instructor" );
    }
}
