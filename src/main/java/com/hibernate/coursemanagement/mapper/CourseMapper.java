package com.hibernate.coursemanagement.mapper;

import com.hibernate.coursemanagement.dto.CourseResponseDTO;
import com.hibernate.coursemanagement.dto.CreateCourseDTO;
import com.hibernate.coursemanagement.dto.UpdateCourseDTO;
import com.hibernate.coursemanagement.entity.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {
    private final InstructorMapper instructorMapper;

    public CourseMapper(InstructorMapper instructorMapper) {
        this.instructorMapper = instructorMapper;
    }

    // convert CreateCourseDTO to entity
    // note:The instructor must be assigned later in the service

    public Course toEntity(CreateCourseDTO dto) {
        Course course = new Course();
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setCategory(dto.getCategory());
        course.setLevel(dto.getLevel());
        course.setDuration(dto.getDuration());
        course.setPrice(dto.getPrice());
        course.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : true); // default to true if null
        return course;
    }

    //Update an existing Entity with UpdateCourseDTO
    //The instructor must be updated later in the Service if changed
    public void updateEntity(UpdateCourseDTO dto, Course course) {
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setCategory(dto.getCategory());
        course.setLevel(dto.getLevel());
        course.setDuration(dto.getDuration());
        course.setPrice(dto.getPrice());
        course.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : course.getIsActive());
    }

    // convert entity to CourseResponseDTO
    public CourseResponseDTO toResponseDTO(Course course) {
        CourseResponseDTO dto = new CourseResponseDTO();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setCategory(course.getCategory());
        dto.setLevel(course.getLevel());
        dto.setDuration(course.getDuration());
        dto.setPrice(course.getPrice());
        dto.setIsActive(course.getIsActive());
        dto.setCreatedAt(course.getCreatedAt());

        if(course.getInstructor() != null) {
            dto.setInstructor(instructorMapper.toSummaryDTO(course.getInstructor()));
        }

        return dto;
    }
}
