package ru.digitalleague.university.repository;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.digitalleague.university.model.StudentEntity;
import ru.digitalleague.university.model.TeacherEntity;
import ru.digitalleague.university.model.TeacherStudentEntity;

import java.util.List;

public interface TeacherStudentRepository extends JpaRepository<TeacherStudentEntity, Long> {

    @Query("SELECT s FROM StudentEntity s " +
            "LEFT JOIN TeacherStudentEntity tsl on s.studentId = tsl.studentId " +
            "WHERE tsl.teacherId = :id")
    List<StudentEntity> findStudentsByTeacherId(@Param("id") long id);

    @Query("SELECT t FROM TeacherEntity t " +
            "LEFT JOIN TeacherStudentEntity tsl on t.teacherId = tsl.teacherId " +
            "WHERE tsl.studentId = :id")
    List<TeacherEntity> findTeachersByStudentId(@Param("id") long id);
}
