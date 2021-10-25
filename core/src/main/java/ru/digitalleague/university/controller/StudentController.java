package ru.digitalleague.university.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.digitalleague.university.api.StudentService;
import ru.digitalleague.university.model.StudentEntity;


@RestController
public class StudentController {

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private StudentService studentService;

    /**
     * Получение информации о студенте.
     * @param id номер студента
     * */
    @GetMapping("/findStudent/{id}")
    @ApiOperation(value = "Контроллер поиска студента")
    public ResponseEntity<String> findById(@PathVariable long id) {

        StudentEntity student = studentService.findStudentById(id);
        amqpTemplate.convertAndSend("logging","Найден студент:\n" + student);
        return ResponseEntity.ok("Найден студент: " + student.toString());
    }

    /**
     * Добавление студента.
     * @param studentEntity параметры студента
     * */
    @PostMapping("/addStudent")
    @ApiOperation(value = "Контроллер добавления студента")
    public ResponseEntity<String> addStudent(@RequestBody StudentEntity studentEntity) {
        studentService.addStudent(studentEntity);
        amqpTemplate.convertAndSend("logging","Добавлен студент:\n" + studentEntity);
        return ResponseEntity.ok("Вы добавили студента: " + studentEntity.toString());
    }

    /**
     * Получение информации обо всех студентах.
     * */
    @GetMapping("/getStudents")
    @ApiOperation(value = "Контроллер поиска всех студентов")
    public ResponseEntity<Iterable> getStudent() {
        Iterable<StudentEntity> students = studentService.getStudents();
        amqpTemplate.convertAndSend("logging","Список студентов:\n" + students);
        return ResponseEntity.ok(students);
    }

    /**
     * Изенение информации о студенте.
     * @param id номер студента
     * @param studentEntity параметры студента
     * */
    @PostMapping("/editStudent/{id}")
    @ApiOperation(value = "Контроллер изменения студента")
    public ResponseEntity<String> editStudent(@PathVariable long id, @RequestBody StudentEntity studentEntity) {
        studentService.editStudent(id, studentEntity);
        amqpTemplate.convertAndSend("logging","Изменён студент под номером " + id);
        return ResponseEntity.ok("Вы изменили студента: " + studentEntity.toString());

    }

    /**
     * Удаление информации о студенте.
     * @param id номер студента
     * */
    @PostMapping("/deleteStudent/{id}")
    @ApiOperation(value = "Контроллер удаления студента")
    public ResponseEntity<String> deleteStudent(@PathVariable long id) {
        studentService.deleteStudent(id);
        amqpTemplate.convertAndSend("logging","Удалён студент под номером {}", id);
        return ResponseEntity.ok("Студент с id:" + id + " удалён");
    }
}
