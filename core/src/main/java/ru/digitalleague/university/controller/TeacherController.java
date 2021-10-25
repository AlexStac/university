package ru.digitalleague.university.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.digitalleague.university.api.TeacherService;
import ru.digitalleague.university.model.TeacherEntity;


@RestController
public class TeacherController {
    
    @Autowired
    private AmqpTemplate amqpTemplate;
    
    @Autowired
    private TeacherService teacherService;

    /**
     * Получение информации о преподавателе.
     * @param id номер преподавателя
     * */
    @GetMapping("/findTeacher/{id}")
    @ApiOperation(value = "Контроллер поиска преподавателя")
    public ResponseEntity<String> findById(@PathVariable long id) {

        TeacherEntity teacher = teacherService.findTeacherById(id);
        amqpTemplate.convertAndSend("logging","Найден преподаватель:\n" + teacher);
        return ResponseEntity.ok("Найден преподаватель: " + teacher.toString());
    }

    /**
     * Добавление преподавателя.
     * @param teacherEntity параметры преподавателя
     * */
    @PostMapping("/addTeacher")
    @ApiOperation(value = "Контроллер добавления преподавателя")
    public ResponseEntity<String> addTeacher(@RequestBody TeacherEntity teacherEntity) {
        teacherService.addTeacher(teacherEntity);
        amqpTemplate.convertAndSend("logging","Добавлен преподаватель:\n" + teacherEntity);
        return ResponseEntity.ok("Вы добавили преподавателя: " + teacherEntity.toString());
    }

    /**
     * Получение информации обо всех преподавателях.
     * */
    @GetMapping("/getTeachers")
    @ApiOperation(value = "Контроллер поиска всех преподавателей")
    public ResponseEntity<Iterable> getTeacher() {
        Iterable<TeacherEntity> teachers = teacherService.getTeachers();
        amqpTemplate.convertAndSend("logging","Список преподователей:\n" + teachers);
        return ResponseEntity.ok(teachers);
    }

    /**
     * Изенение информации о преподавателе.
     * @param id номер преподавателя
     * @param teacherEntity параметры преподавателя
     * */
    @PostMapping("/editTeacher/{id}")
    @ApiOperation(value = "Контроллер изменения преподавателя")
    public ResponseEntity<String> editTeacher(@PathVariable long id, @RequestBody TeacherEntity teacherEntity) {
        teacherService.editTeacher(id, teacherEntity);
        amqpTemplate.convertAndSend("logging","Изменён преподаватель под номером " + id);
        return ResponseEntity.ok("Вы изменили преподавателя: " + teacherEntity.toString());

    }

    /**
     * Удаление информации о преподавателе.
     * @param id номер преподавателя
     * */
    @PostMapping("/deleteTeacher/{id}")
    @ApiOperation(value = "Контроллер удаления преподавателя")
    public ResponseEntity<String> deleteTeacher(@PathVariable long id) {
        teacherService.deleteTeacher(id);
        amqpTemplate.convertAndSend("logging","Удалён преподаватель под номером " + id);
        return ResponseEntity.ok("Преподаватель с id:" + id + " удалён");
    }
}
