package com.hibernate.coursemanagement.repository;

import com.hibernate.coursemanagement.entity.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    //find by email
    Optional<Instructor> findByEmail(String email);

    //check if exist an email
    boolean existsByEmail(String email);

    //search for instructors by specialty
    List<Instructor> findBySpecialtyContainingIgnoreCase(String specialty);

    //Search for instructors with years of experience greater than or equal to
    List <Instructor> findByYearsOfExperienceGreaterThanEqual(Integer years);
}
