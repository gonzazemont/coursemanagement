package com.hibernate.coursemanagement.controller;

import com.hibernate.coursemanagement.dto.CourseResponseDTO;
import com.hibernate.coursemanagement.dto.CreateCourseDTO;
import com.hibernate.coursemanagement.dto.UpdateCourseDTO;
import com.hibernate.coursemanagement.entity.CourseCategory;
import com.hibernate.coursemanagement.entity.CourseLevel;
import com.hibernate.coursemanagement.service.CourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/courses")
public class CourseController {

    public final CourseService courseService;

    //create course
    @PostMapping
    public ResponseEntity<CourseResponseDTO> createCourse(
            @Valid @RequestBody CreateCourseDTO createCourseDTO
            ) {
        CourseResponseDTO createdCourse = courseService.createCourse(createCourseDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCourse);
    }

    //get all courses
    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        List<CourseResponseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    //get course by id
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long id) {
        CourseResponseDTO course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }

    //update course
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCourseDTO updateCourseDTO
    ) {
        CourseResponseDTO updatedCourse = courseService.updateCourse(id, updateCourseDTO);
        return ResponseEntity.ok(updatedCourse);
    }

    //delete course
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    //patch - enable or disable status (toggle)
    @PatchMapping("/{id}/toggle-status")
    public ResponseEntity<CourseResponseDTO> toggleCourseStatus(@PathVariable Long id) {
        CourseResponseDTO updatedCourse = courseService.toggleCourseStatus(id);
        return ResponseEntity.ok(updatedCourse);
    }

    //search courses by category
    @GetMapping("/search/by-category")
    public ResponseEntity<List<CourseResponseDTO>> getCoursesByCategory(
            @RequestParam CourseCategory category) {
        List<CourseResponseDTO> courses = courseService.getCoursesByCategory(category);
        return ResponseEntity.ok(courses);
    }

    //search courses by level
    @GetMapping("/search/by-level")
    public ResponseEntity<List<CourseResponseDTO>> getCoursesByLevel(
            @RequestParam CourseLevel level) {
        List<CourseResponseDTO> courses = courseService.getCoursesByLevel(level);
        return ResponseEntity.ok(courses);
    }

    //search courses by active status
    @GetMapping("/active")
    public ResponseEntity<List<CourseResponseDTO>> getActiveCourses(){
        List<CourseResponseDTO> courses = courseService.getActiveCourses();
        return ResponseEntity.ok(courses);
    }

    //search courses by range of price
    @GetMapping("/search/by-price")
    public ResponseEntity<List<CourseResponseDTO>> getCoursesByPriceRange(
            @RequestParam Double minPrice,
            @RequestParam Double maxPrice) {
        List<CourseResponseDTO> courses = courseService.getCoursesByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(courses);
    }

    //search courses by title
    @GetMapping("/search/by-title")
    public ResponseEntity<List<CourseResponseDTO>> getCoursesByTitle(
            @RequestParam String title) {
        List<CourseResponseDTO> courses = courseService.searchCoursesByTitle(title);
        return ResponseEntity.ok(courses);
    }
}
