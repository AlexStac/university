package ru.digitalleague.university.model;

import lombok.Data;


import javax.persistence.*;

@Entity
@Table(name = "student")
@Data
public class StudentEntity {

    /**
     * Идентификатор студента.
     */
    @Id
    @Column(name = "student_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_seq")
    @SequenceGenerator(name = "student_seq", sequenceName = "student_seq", allocationSize = 1)
    private Long studentId;

    /**
     * ФИО студента.
     */
    @Column(name = "fio_student")
    private String fioStudent;

    /**
     * Специальность студента.
     */
    @Column(name = "specification_student")
    private String specification;

    /**
     * Курс обучения.
     */
    @Column(name = "course")
    private int course;
}
