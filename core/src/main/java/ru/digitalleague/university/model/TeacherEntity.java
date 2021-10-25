package ru.digitalleague.university.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "teacher")
@Data
public class TeacherEntity {

    /**
     * Идентификатор преподавателя.
     */
    @Id
    @Column(name = "teacher_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_seq")
    @SequenceGenerator(name = "teacher_seq", sequenceName = "teacher_seq", allocationSize = 1)
    private Long teacherId;

    /**
     * ФИО преподавателя.
     */
    @Column(name = "fio_teacher")
    private String fioTeacher;

    /**
     * Кафедра преподавателя.
     */
    @Column(name = "department_teacher")
    private String departmentTeacher;
}
