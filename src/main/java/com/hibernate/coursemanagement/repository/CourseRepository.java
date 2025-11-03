package com.hibernate.coursemanagement.repository;

import com.hibernate.coursemanagement.entity.Course;
import com.hibernate.coursemanagement.entity.CourseCategory;
import com.hibernate.coursemanagement.entity.CourseLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    /*search for courses by instructor id:
    Spring Data JPA understands that instructor is a relationship
    Automatically performs a JOIN with the instructors table
    */
    List<Course> findByInstructorId(Long instructorId);

    //search for courses by category
    List<Course> findByCategoryContainingIgnoreCase(CourseCategory category);

    //search for courses by level
    List<Course> findByLevelContainingIgnoreCase(CourseLevel level);

    //search for active courses
    List<Course> findByIsActiveTrue();

    //search for inactive courses
    List<Course> findByIsActiveFalse();

    //search for active courses by category
    List<Course> findByCategoryAndIsActiveTrue(CourseCategory category);

    //search for range of prices
    List<Course> findByPriceBetween(Double minPrice, Double maxPrice);

    //Search for courses whose title contains text
    List<Course> findByTitleContainingIgnoreCase(String title);

    //search for courses of instructor that are active
    //SELECT * FROM courses WHERE instructor_id = ? AND is_active = true
    List<Course> findByInstructorIdAndIsActiveTrue(Long instructorId);

    //personalized query: Courses taught by an instructor with a specific category
    //JPQL (Java Persistence Query Language)
    @Query("SELECT c FROM Course c WHERE c.instructor.id = :instructorId AND c.category = :category")
    List<Course> findCoursesByInstructorAndCategory(
            @Param("instructorId") Long instructorId,
            @Param("category") CourseCategory category
    );

}
