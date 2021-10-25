package ru.digitalleague.university.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.university.api.StudentService;
import ru.digitalleague.university.model.StudentEntity;
import ru.digitalleague.university.repository.StudentRepository;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    /**
     * Получение информации о студенте.
     * @param id номер студента
     * */
    @Override
    public StudentEntity findStudentById(Long id) {

        return studentRepository.findById(id).get();
    }

    /**
     * Добавление студента.
     * @param studentEntity параметры студента
     * */
    @Override
    public void addStudent(StudentEntity studentEntity) {
        studentRepository.save(studentEntity);
    }

    /**
     * Получение информации обо всех студентах.
     * */
    @Override
    public Iterable<StudentEntity> getStudents() {
        return studentRepository.findAll();
    }

    /**
     * Изенение информации о студенте.
     * @param id номер студента
     * @param studentEntity параметры студента
     * */
    @Override
    public void editStudent(Long id, StudentEntity studentEntity) {
        if (!studentRepository.existsById(id)) {
            log.info("Student with id " + id + " not found");
        } else {
            StudentEntity studentById = studentRepository.findById(id).get();
            studentById.setFioStudent(studentEntity.getFioStudent());
            studentById.setSpecification(studentEntity.getSpecification());
            studentById.setCourse(studentEntity.getCourse());
            studentRepository.save(studentById);
        }
    }

    /**
     * Удаление информации о студенте.
     * @param id номер студента
     * */
    @Override
    public void deleteStudent(Long id) {
        StudentEntity delStudent = studentRepository.findById(id).get();
        studentRepository.delete(delStudent);
    }
}
