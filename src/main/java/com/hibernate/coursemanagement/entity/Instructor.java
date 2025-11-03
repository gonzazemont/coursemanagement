package com.hibernate.coursemanagement.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructors")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(length = 100)
    private String specialty;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @Column(name = "years_of_experience")
    private Integer yearsOfExperience;

    @Column (name = "created_at", updatable = false)
    private LocalDate createdAt;


    // RELATIONSHIP: An instructor has many courses.
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Course> courses = new ArrayList<>();


    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
    }
}
