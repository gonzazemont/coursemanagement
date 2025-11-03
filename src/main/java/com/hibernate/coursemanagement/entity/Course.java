package com.hibernate.coursemanagement.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private CourseCategory category;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private CourseLevel level;

    @Column(nullable = false)
    private Integer duration;

    @Column(nullable = false)
    private Double price;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "created_at", updatable = false)
    private LocalDate createdAt;



    // RELATIONSHIP: Many courses belong to one instructor.
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", nullable = false)
    @JsonBackReference
    private Instructor instructor;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
        if (isActive == null) {
            isActive = true;
        }
    }
}
