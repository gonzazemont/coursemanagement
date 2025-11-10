package com.hibernate.coursemanagement.service;

import com.hibernate.coursemanagement.dto.CourseResponseDTO;
import com.hibernate.coursemanagement.dto.CreateInstructorDTO;
import com.hibernate.coursemanagement.dto.InstructorResponseDTO;
import com.hibernate.coursemanagement.dto.UpdateInstructorDTO;
import com.hibernate.coursemanagement.entity.Instructor;
import com.hibernate.coursemanagement.exception.DuplicateEmailException;
import com.hibernate.coursemanagement.exception.InstructorHasCoursesException;
import com.hibernate.coursemanagement.exception.InstructorNotFoundException;
import com.hibernate.coursemanagement.mapper.CourseMapper;
import com.hibernate.coursemanagement.mapper.InstructorMapper;
import com.hibernate.coursemanagement.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class InstructorServiceImpl implements InstructorService{
    private final InstructorRepository instructorRepository;
    private final InstructorMapper instructorMapper;
    private final CourseMapper courseMapper;

    @Override
    public InstructorResponseDTO createIntructor(CreateInstructorDTO createInstructorDTO) {
        //verify email uniqueness
        if(instructorRepository.existsByEmail(createInstructorDTO.getEmail())){
            throw new DuplicateEmailException(createInstructorDTO.getEmail());
        }

        //convert DTO to entity
        Instructor instructor = instructorMapper.toEntity(createInstructorDTO);

        //save to database
        Instructor savedInstructor = instructorRepository.save(instructor);

        //convert entity to DTO and return
        return instructorMapper.toResponseDTO (savedInstructor);
    }

    @Override
    @Transactional(readOnly = true)
    public InstructorResponseDTO getInstructorById(Long id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new InstructorNotFoundException(id));

        return instructorMapper.toResponseDTO(instructor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InstructorResponseDTO> getAllInstructors() {
        List<Instructor> instructors = instructorRepository.findAll();
        return instructors.stream()
                .map(instructorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public InstructorResponseDTO updateInstructor(Long id, UpdateInstructorDTO updateInstructorDTO) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new InstructorNotFoundException(id));

        //check email uniqueness if changed
        if (!instructor.getEmail().equals(updateInstructorDTO.getEmail()) &&
                instructorRepository.existsByEmail(updateInstructorDTO.getEmail())) {
            throw new DuplicateEmailException(updateInstructorDTO.getEmail());
        }

        //update entity
        instructorMapper.updateEntity(updateInstructorDTO, instructor);

        //save updated entity
        Instructor updatedInstructor = instructorRepository.save(instructor);

        return instructorMapper.toResponseDTO(updatedInstructor);
    }

    @Override
    public void deleteInstructor(Long id) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new InstructorNotFoundException(id));
        //verify that the instructor has no courses assigned
        if (!instructor.getCourses().isEmpty()) {
            throw new InstructorHasCoursesException(id, instructor.getCourses().size());
        }

        instructorRepository.delete(instructor);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InstructorResponseDTO> getInstructorsBySpecialty(String specialty) {
        List<Instructor> instructors = instructorRepository.findBySpecialtyContainingIgnoreCase(specialty);

        return instructors.stream()
                .map(instructorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<InstructorResponseDTO> getInstructorsByExperience(Integer minYears) {
        List<Instructor> instructors = instructorRepository.findByYearsOfExperienceGreaterThanEqual(minYears);

        return instructors.stream()
                .map(instructorMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponseDTO> getCoursesByInstructorId(Long instructorId) {
        Instructor instructor = instructorRepository.findById(instructorId)
                .orElseThrow(() -> new InstructorNotFoundException(instructorId));

        return instructor.getCourses().stream()
                .map(courseMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}
