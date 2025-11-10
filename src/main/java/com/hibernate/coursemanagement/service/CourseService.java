package com.hibernate.coursemanagement.service;

import com.hibernate.coursemanagement.dto.CourseResponseDTO;
import com.hibernate.coursemanagement.dto.CreateCourseDTO;
import com.hibernate.coursemanagement.dto.UpdateCourseDTO;
import com.hibernate.coursemanagement.entity.CourseCategory;
import com.hibernate.coursemanagement.entity.CourseLevel;

import java.util.List;

public interface CourseService {

    CourseResponseDTO createCourse(CreateCourseDTO createCourseDTO);
    CourseResponseDTO getCourseById(Long id);
    List<CourseResponseDTO> getAllCourses();
    CourseResponseDTO updateCourse(Long id, UpdateCourseDTO updateCourseDTO);
    void deleteCourse(Long id);

    // partial update
    CourseResponseDTO toggleCourseStatus(Long id);

    // searches
    List<CourseResponseDTO> getCoursesByCategory(CourseCategory category);
    List<CourseResponseDTO> getCoursesByLevel(CourseLevel level);
    List<CourseResponseDTO> getActiveCourses();
    List<CourseResponseDTO> getCoursesByPriceRange(Double minPrice, Double maxPrice);
    List<CourseResponseDTO> searchCoursesByTitle(String title);
}
