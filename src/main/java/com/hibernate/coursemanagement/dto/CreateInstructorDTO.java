package com.hibernate.coursemanagement.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInstructorDTO {

    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    private String email;

    @Size(max = 100, message = "Specialty must be at most 100 characters")
    private String specialty;

    @Size(max = 1000, message = "Bio must be at most 1000 characters")
    private String bio;

    @Min(value = 0, message = "Years of experience must be non-negative")
    @Max(value = 50, message = "Years of experience must be less than or equal to 50")
    private Integer yearsOfExperience;
}
