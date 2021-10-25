alter table teacher_student_link add constraint tacher_student_uq
    unique (teacher_id, student_id)