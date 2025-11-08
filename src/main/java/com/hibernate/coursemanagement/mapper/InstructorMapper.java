package com.hibernate.coursemanagement.mapper;

import com.hibernate.coursemanagement.dto.CreateInstructorDTO;
import com.hibernate.coursemanagement.dto.InstructorResponseDTO;
import com.hibernate.coursemanagement.dto.InstructorSummaryDTO;
import com.hibernate.coursemanagement.dto.UpdateInstructorDTO;
import com.hibernate.coursemanagement.entity.Instructor;
import org.springframework.stereotype.Component;

@Component
public class InstructorMapper {
    // convert CreateInstructorDTO to entity
    public Instructor toEntity(CreateInstructorDTO dto) {
        Instructor instructor = new Instructor();
        instructor.setFirstName((dto.getFirstName()));
        instructor.setLastName(dto.getLastName());
        instructor.setEmail(dto.getEmail());
        instructor.setSpecialty(dto.getSpecialty());
        instructor.setBio(dto.getBio());
        instructor.setYearsOfExperience(dto.getYearsOfExperience());
        return instructor;
    }

    // update an existing entity with UpdateInstructorDTO
    public void updateEntity(UpdateInstructorDTO dto, Instructor instructor) {
        instructor.setFirstName((dto.getFirstName()));
        instructor.setLastName(dto.getLastName());
        instructor.setEmail(dto.getEmail());
        instructor.setSpecialty(dto.getSpecialty());
        instructor.setBio(dto.getBio());
        instructor.setYearsOfExperience(dto.getYearsOfExperience());
    }

    //covert entity to InstructorResponseDTO
    public InstructorResponseDTO toResponseDTO(Instructor instructor) {
        InstructorResponseDTO dto = new InstructorResponseDTO();
        dto.setId(instructor.getId());
        dto.setFirstName(instructor.getFirstName());
        dto.setLastName(instructor.getLastName());
        dto.setEmail(instructor.getEmail());
        dto.setSpecialty(instructor.getSpecialty());
        dto.setBio(instructor.getBio());
        dto.setYearsOfExperience(instructor.getYearsOfExperience());
        return dto;
    }

    // convert entity to  InstructorSummaryDTO
    public InstructorSummaryDTO toSummaryDTO(Instructor instructor) {
        InstructorSummaryDTO dto = new InstructorSummaryDTO();
        dto.setId(instructor.getId());
        dto.setFirstName(instructor.getFirstName());
        dto.setLastName(instructor.getLastName());
        return dto;
    }
}
