package ru.julia.hibernatedemo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "students")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OtusStudent {
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    ------------------------------------------------------------------------------
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @SequenceGenerator(initialValue = 1, allocationSize = 1, name = "idgen",
//      sequenceName = "otus_students_seq")
//    ------------------------------------------------------------------------------
//    @GeneratedValue(strategy = GenerationType.TABLE)
//    ------------------------------------------------------------------------------
//    @GeneratedValue(strategy = GenerationType.UUID)
//    @UuidGenerator
//    private UUID id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "student_name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "student_id")
    private List<EMail> emails;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "student_courses",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;
}