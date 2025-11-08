package com.hibernate.coursemanagement.dto;

import com.hibernate.coursemanagement.entity.CourseCategory;
import com.hibernate.coursemanagement.entity.CourseLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDTO {
    private Long id;
    private String title;
    private String description;
    private CourseCategory category;
    private CourseLevel level;
    private Integer duration;
    private Double price;
    private Boolean isActive;
    private LocalDate createdAt;

    private InstructorSummaryDTO instructor;
}
