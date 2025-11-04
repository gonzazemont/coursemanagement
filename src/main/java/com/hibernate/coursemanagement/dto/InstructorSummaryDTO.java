package com.hibernate.coursemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstructorSummaryDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String specialty;

    //we will use this DTO when we need basic information about the instructor within a course, etc.
}
