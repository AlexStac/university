package ru.digitalleague.university.api;

import ru.digitalleague.university.model.StudentEntity;

/**
 * Сервис работы с таблицей студентов.
 * */
public interface StudentService {

    StudentEntity findStudentById(Long id);

    void addStudent(StudentEntity studentEntity);

    Iterable<StudentEntity> getStudents();

    void editStudent(Long id, StudentEntity studentEntity);

    void deleteStudent(Long id);

}
