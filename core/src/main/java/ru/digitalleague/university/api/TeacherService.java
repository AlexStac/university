package ru.digitalleague.university.api;


import ru.digitalleague.university.model.TeacherEntity;

/**
 * Сервис работы с таблицей преподавателей.
 * */
public interface TeacherService {

    TeacherEntity findTeacherById(Long id);

    void addTeacher(TeacherEntity teacherEntity);

    Iterable<TeacherEntity> getTeachers();

    void editTeacher(Long id, TeacherEntity teacherEntity);

    void deleteTeacher(Long id);
}
