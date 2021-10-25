package ru.digitalleague.university.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.digitalleague.university.api.TeacherStudentService;
import ru.digitalleague.university.model.StudentEntity;
import ru.digitalleague.university.model.TeacherEntity;
import ru.digitalleague.university.model.TeacherStudentEntity;

@RestController
public class TeacherStudentController {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private TeacherStudentService teacherStudentService;

    /**
     * Получение информации обо всех преподавателях.
     * @param id номер студента
     * */
    @GetMapping("/findTeachersByStudent/{id}")
    @ApiOperation(value = "Контроллер поиска преподавателей")
    public ResponseEntity<Iterable> findTeachersByStudent(@PathVariable Long id) {
        Iterable<TeacherEntity> teachers = teacherStudentService.findTeachersByStudent(id);
        amqpTemplate.convertAndSend("logging", "Список преподователей:\n" + teachers);
        return ResponseEntity.ok(teachers);
    }

    /**
     * Получение информации обо всех студентах.
     * @param id номер преподавателя
     * */
    @GetMapping("/findStudentsByTeachers/{id}")
    @ApiOperation(value = "Контроллер поиска студентов")
    public ResponseEntity<Iterable> findStudentsByTeachers(@PathVariable Long id) {
        Iterable<StudentEntity> students = teacherStudentService.findStudentsByTeacher(id);
        amqpTemplate.convertAndSend("logging", "Список студентов:\n" + students);
        return ResponseEntity.ok(students);
    }

    /**
     * Добавление зависимости.
     * @param teacherStudentEntity детали зависимости
     * */
    @PostMapping("/addTeacherStudent")
    @ApiOperation(value = "Контроллер добавления связи")
    public ResponseEntity<String> addTeacherStudent(@RequestBody TeacherStudentEntity teacherStudentEntity) {
        teacherStudentService.addTeacherStudent(teacherStudentEntity);
        amqpTemplate.convertAndSend("logging", "Добавлены преподаватель и студент:\n" + teacherStudentEntity);
        return ResponseEntity.ok("Вы добавили преподавателя и студента: " + teacherStudentEntity.toString());
    }

    /**
     * Удаление зависимости.
     * @param id номер зависимости
     * */
    @PostMapping("/deleteTeacherStudent/{id}")
    @ApiOperation(value = "Контроллер удаления связи")
    public ResponseEntity<String> deleteTeacherStudent(@PathVariable Long id) {
        teacherStudentService.deleteTeacherStudent(id);
        amqpTemplate.convertAndSend("logging","Удалена связь " + id);
        return ResponseEntity.ok("Связь преподаватель-студент с id:" + id + " удалёна");
    }
}
