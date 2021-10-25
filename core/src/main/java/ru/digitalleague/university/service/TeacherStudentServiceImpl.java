package ru.digitalleague.university.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.university.api.TeacherStudentService;
import ru.digitalleague.university.model.StudentEntity;
import ru.digitalleague.university.model.TeacherEntity;
import ru.digitalleague.university.model.TeacherStudentEntity;
import ru.digitalleague.university.repository.TeacherStudentRepository;

@Service
@Slf4j
public class TeacherStudentServiceImpl implements TeacherStudentService {

    @Autowired
    private TeacherStudentRepository teacherStudentRepository;

    /**
     * Получение информации обо всех преподавателях.
     * @param id номер студента
     * */
    @Override
    public Iterable<StudentEntity> findStudentsByTeacher(Long id) {
        return teacherStudentRepository.findStudentsByTeacherId(id);
    }

    /**
     * Получение информации обо всех студентах.
     * @param id номер преподавателя
     * */
    @Override
    public Iterable<TeacherEntity> findTeachersByStudent(Long id) {
        return teacherStudentRepository.findTeachersByStudentId(id);
    }

    /**
     * Добавление зависимости.
     * @param teacherStudentEntity детали зависимости
     * */
    @Override
    public void addTeacherStudent(TeacherStudentEntity teacherStudentEntity) {
        teacherStudentRepository.save(teacherStudentEntity);
    }

    /**
     * Удаление зависимости.
     * @param id номер зависимости
     * */
    @Override
    public void deleteTeacherStudent(Long id) {
        TeacherStudentEntity delTeacherStud = teacherStudentRepository.findById(id).get();
        teacherStudentRepository.delete(delTeacherStud);
    }
}
