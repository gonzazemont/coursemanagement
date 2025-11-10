package com.hibernate.coursemanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstructorResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String specialty;
    private String bio;
    private Integer yearsOfExperience;
}
