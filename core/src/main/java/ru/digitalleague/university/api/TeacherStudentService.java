package ru.digitalleague.university.api;

import ru.digitalleague.university.model.StudentEntity;
import ru.digitalleague.university.model.TeacherEntity;
import ru.digitalleague.university.model.TeacherStudentEntity;

/**
 * Сервис работы с таблицей связей.
 * */
public interface TeacherStudentService {

    Iterable<StudentEntity> findStudentsByTeacher(Long id);

    Iterable<TeacherEntity> findTeachersByStudent(Long id);

    void addTeacherStudent(TeacherStudentEntity teacherStudentEntity);

    void deleteTeacherStudent(Long id);
}
