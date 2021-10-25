package ru.digitalleague.university.model;

import lombok.Data;


import javax.persistence.*;

@Entity
@Table(name = "teacher_student_link")
@Data
public class TeacherStudentEntity {

    /**
     * Идентификатор связи.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_student_seq")
    @SequenceGenerator(name = "teacher_student_seq", sequenceName = "teacher_student_seq", allocationSize = 1)
    private Long id;

    /**
     * Идентификатор преподавателя.
     */
    @Column(name = "teacher_id")
    private Long teacherId;

    /**
     * Идентификатор студента.
     */
    @Column(name = "student_id")
    private Long studentId;
}
