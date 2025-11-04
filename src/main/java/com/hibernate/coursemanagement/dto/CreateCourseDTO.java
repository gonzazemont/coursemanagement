package com.hibernate.coursemanagement.dto;

import com.hibernate.coursemanagement.entity.CourseCategory;
import com.hibernate.coursemanagement.entity.CourseLevel;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCourseDTO {

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 200, message = "Title must be between 3 and 200 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 200, message = "Description must be at least 10 characters")
    private String description;

    @NotNull(message = "Category in is required")
    private CourseCategory category;

    @NotNull(message = "Level is required")
    private CourseLevel level;

    @NotNull(message = "Duration in hours is required")
    @Min( value = 1, message = "Duration must be at least 1 hour")
    @Max( value = 500, message = "Duration must be at most 500 hours")
    private Integer duration;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", message = "Price must be at least 0")
    @DecimalMax(value = "10000.0", message = "Price must not exceed 10000")
    private Double price;


    private Boolean isActive;

    @NotNull(message = "Instructor ID is required")
    private Integer instructorId;
}
