package com.hibernate.coursemanagement.service;

import com.hibernate.coursemanagement.dto.CourseResponseDTO;
import com.hibernate.coursemanagement.dto.CreateInstructorDTO;
import com.hibernate.coursemanagement.dto.InstructorResponseDTO;
import com.hibernate.coursemanagement.dto.UpdateInstructorDTO;

import java.util.List;

public interface InstructorService {

    InstructorResponseDTO createIntructor(CreateInstructorDTO createInstructorDTO);
    InstructorResponseDTO getInstructorById(Long id);
    List<InstructorResponseDTO> getAllInstructors();
    InstructorResponseDTO updateInstructor(Long id, UpdateInstructorDTO updateInstructorDTO);
    void deleteInstructor(Long id);

    // specific search methods
    List<InstructorResponseDTO> getInstructorsBySpecialty(String specialty);
    List<InstructorResponseDTO> getInstructorsByExperience(Integer minYears);

    //method for obtaining an instructor's courses
    List<CourseResponseDTO> getCoursesByInstructorId(Long instructorId);
}
