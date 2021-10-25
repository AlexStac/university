create sequence if not exists teacher_seq start 1;

create table university.teacher
(
    teacher_id bigint not null default (nextval('teacher_seq'))
        constraint teacher_pk
            primary key,
    fio_teacher varchar(256) not null,
    department_teacher text not null
);

comment on column university.teacher.teacher_id is 'Уникальный идентификатор';
comment on column university.teacher.fio_teacher is 'Фамилия Имя Отчество преподавателя';
comment on column university.teacher.department_teacher is 'Кафедра преподавателя';