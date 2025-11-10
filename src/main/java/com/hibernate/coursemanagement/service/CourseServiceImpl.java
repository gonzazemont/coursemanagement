package com.hibernate.coursemanagement.service;

import com.hibernate.coursemanagement.dto.CourseResponseDTO;
import com.hibernate.coursemanagement.dto.CreateCourseDTO;
import com.hibernate.coursemanagement.dto.UpdateCourseDTO;
import com.hibernate.coursemanagement.entity.Course;
import com.hibernate.coursemanagement.entity.CourseCategory;
import com.hibernate.coursemanagement.entity.CourseLevel;
import com.hibernate.coursemanagement.entity.Instructor;
import com.hibernate.coursemanagement.exception.CourseNotFoundException;
import com.hibernate.coursemanagement.exception.InstructorNotFoundException;
import com.hibernate.coursemanagement.mapper.CourseMapper;
import com.hibernate.coursemanagement.repository.CourseRepository;
import com.hibernate.coursemanagement.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseServiceImpl implements CourseService{
    private final CourseRepository courseRepository;
    private final InstructorRepository instructorRepository;
    private final CourseMapper courseMapper;

    @Override
    public CourseResponseDTO createCourse(CreateCourseDTO createCourseDTO) {
        //step 1:search for the instructor by id
        Instructor instructor = instructorRepository.findById(createCourseDTO.getInstructorId())
                .orElseThrow(() -> new InstructorNotFoundException(createCourseDTO.getInstructorId()));

        //step2: Convert DTO to Entity
        Course course = courseMapper.toEntity(createCourseDTO);

        //step 3:assign the instructor to the course
        course.setInstructor(instructor);

        //step 4:save to database
        Course savedCourse = courseRepository.save(course);

        //step 5:Convert Entity to DTO and return
        return courseMapper.toResponseDTO(savedCourse);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseResponseDTO getCourseById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));

        return courseMapper.toResponseDTO(course);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponseDTO> getAllCourses() {
        List<Course> courses = courseRepository.findAll();
        return courses.stream()
                .map(courseMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponseDTO updateCourse(Long id, UpdateCourseDTO updateCourseDTO) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));

        if (!existingCourse.getInstructor().getId().equals(updateCourseDTO.getInstructorId())) {
            //find new instructor
            Instructor newInstructor = instructorRepository.findById(updateCourseDTO.getInstructorId())
                    .orElseThrow(() -> new InstructorNotFoundException(updateCourseDTO.getInstructorId()));

            existingCourse.setInstructor(newInstructor);
        }

        courseMapper.updateEntity(updateCourseDTO, existingCourse);

        Course updatedCourse = courseRepository.save(existingCourse);

        return courseMapper.toResponseDTO(updatedCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        if (!courseRepository.existsById(id)) {
            throw new CourseNotFoundException(id);
        }
        courseRepository.deleteById(id);
    }

    @Override
    public CourseResponseDTO toggleCourseStatus(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(id));

        //switch the isActive status
        course.setIsActive(!course.getIsActive());

        Course updatedCourse = courseRepository.save(course);

        return courseMapper.toResponseDTO(updatedCourse);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponseDTO> getCoursesByCategory(CourseCategory category) {
        List<Course> courses = courseRepository.findByCategory(category);
        return courses.stream()
                .map(courseMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponseDTO> getCoursesByLevel(CourseLevel level) {
        List<Course> courses = courseRepository.findByLevel(level);
        return courses.stream()
                .map(courseMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponseDTO> getActiveCourses() {
        List<Course> courses = courseRepository.findByIsActiveTrue();
        return courses.stream()
                .map(courseMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponseDTO> getCoursesByPriceRange(Double minPrice, Double maxPrice) {
        List<Course> courses = courseRepository.findByPriceBetween(minPrice, maxPrice);
        return courses.stream()
                .map(courseMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponseDTO> searchCoursesByTitle(String title) {
        List<Course> courses = courseRepository.findByTitleContainingIgnoreCase(title);
        return courses.stream()
                .map(courseMapper::toResponseDTO)
                .collect(Collectors.toList());
    }
}

