create sequence if not exists student_seq start 1;

create table university.student
(
    student_id             bigint not null default (nextval('student_seq'))
        constraint student_pk primary key,
    fio_student            varchar(256) not null,
    specification_student text         not null,
    course         int          not null
);

comment on table university.student is 'Таблица студентов';
comment on column university.student.student_id is 'Уникальный идентификатор';
comment on column university.student.fio_student is 'Фамилия Имя Отчество студента';
comment on column university.student.specification_student is 'Специальность студента';
comment on column university.student.course is 'Курс обучения студента';
