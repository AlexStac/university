package ru.digitalleague.university.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.university.api.TeacherService;
import ru.digitalleague.university.model.TeacherEntity;
import ru.digitalleague.university.repository.TeacherRepository;

@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    /**
     * Получение информации о преподавателе.
     * @param id номер преподавателя
     * */
    @Override
    public TeacherEntity findTeacherById(Long id) {
        return teacherRepository.findById(id).get();
    }

    /**
     * Добавление преподавателя.
     * @param teacherEntity параметры преподавателя
     * */
    @Override
    public void addTeacher(TeacherEntity teacherEntity) {
        teacherRepository.save(teacherEntity);
    }

    /**
     * Получение информации обо всех преподавателях.
     * */
    @Override
    public Iterable<TeacherEntity> getTeachers() {
        return teacherRepository.findAll();
    }

    /**
     * Изенение информации о преподавателе.
     * @param id номер преподавателя
     * @param teacherEntity параметры преподавателя
     * */
    @Override
    public void editTeacher(Long id, TeacherEntity teacherEntity) {
        if (!teacherRepository.existsById(id)) {
            log.info("Teacher with id " + id + " not found");
        } else {
            TeacherEntity teacherById = teacherRepository.findById(id).get();
            teacherById.setFioTeacher(teacherEntity.getFioTeacher());
            teacherById.setDepartmentTeacher(teacherEntity.getDepartmentTeacher());
            teacherRepository.save(teacherById);
        }
    }

    /**
     * Удаление информации о преподавателе.
     * @param id номер преподавателя
     * */
    @Override
    public void deleteTeacher(Long id) {
        TeacherEntity delTacher = teacherRepository.findById(id).get();
        teacherRepository.delete(delTacher);
    }
}
