create sequence if not exists teacher_student_seq start 1;

create table university.teacher_student_link
(
    id bigint not null default (nextval('teacher_student_seq'))
        constraint teacher_student_pk
            primary key,
    teacher_id bigint not null,
    student_id bigint not null,
    foreign key (teacher_id) references university.teacher(teacher_id),
    foreign key (student_id) references university.student(student_id)

);

comment on column university.teacher_student_link.teacher_id is 'Уникальный идентификатор преподавателя';
comment on column university.teacher_student_link.student_id is 'Уникальный идентификатор студента';