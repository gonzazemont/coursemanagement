package com.hibernate.coursemanagement.controller;

import com.hibernate.coursemanagement.dto.CourseResponseDTO;
import com.hibernate.coursemanagement.dto.CreateInstructorDTO;
import com.hibernate.coursemanagement.dto.InstructorResponseDTO;
import com.hibernate.coursemanagement.dto.UpdateInstructorDTO;
import com.hibernate.coursemanagement.service.InstructorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/instructors")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    //create instructor
    @PostMapping
    public ResponseEntity<InstructorResponseDTO> createInstructor(
            @Valid @RequestBody CreateInstructorDTO createInstructorDTO
            ) {
        InstructorResponseDTO createdInstructor = instructorService.createIntructor(createInstructorDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdInstructor);
    }

    //read - get all instructors
    @GetMapping
    public ResponseEntity<List<InstructorResponseDTO>> getAllInstructors() {
        List<InstructorResponseDTO> instructors = instructorService.getAllInstructors();
        return ResponseEntity.ok(instructors);
    }

    //read - get instructor by id
    @GetMapping("/{id}")
    public ResponseEntity<InstructorResponseDTO> getInstructorById(@PathVariable Long id) {
        InstructorResponseDTO instructor = instructorService.getInstructorById(id);
        return ResponseEntity.ok(instructor);
    }

    //update instructor
    @PutMapping("/{id}")
    public ResponseEntity<InstructorResponseDTO> updateInstructor(
            @PathVariable Long id,
            @Valid @RequestBody UpdateInstructorDTO updateInstructorDTO
    ) {
        InstructorResponseDTO updatedInstructor = instructorService.updateInstructor(id, updateInstructorDTO);
        return ResponseEntity.ok(updatedInstructor);
    }

    //delete instructor
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.noContent().build();
    }

    //search - find by specialty
    @GetMapping("/search/by-specialty")
    public ResponseEntity<List<InstructorResponseDTO>> getInstructorsBySpecialty(
            @RequestParam String specialty) {
        List<InstructorResponseDTO> instructors = instructorService.getInstructorsBySpecialty(specialty);
        return ResponseEntity.ok(instructors);
    }

    //search - find by experience
    @GetMapping("/search/by-experience")
    public ResponseEntity<List<InstructorResponseDTO>> getInstructorsByExperience(
            @RequestParam Integer minYears) {
        List<InstructorResponseDTO> instructors = instructorService.getInstructorsByExperience(minYears);
        return ResponseEntity.ok(instructors);
    }

    //relationship - get courses by instructor id
    @GetMapping("/{id}/courses")
    public ResponseEntity<List<CourseResponseDTO>> getCoursesByInstructorId(@PathVariable Long id) {
        List<CourseResponseDTO> courses = instructorService.getCoursesByInstructorId(id);
        return ResponseEntity.ok(courses);
    }

}
