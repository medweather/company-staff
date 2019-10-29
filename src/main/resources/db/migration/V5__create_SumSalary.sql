create table sum_salary(
    id serial,
    department_id int4,
    department_name varchar(255),
    salary_fund bigint,
    primary key (id)
);

alter table if exists sum_salary
    add constraint sum_salary_department_fk
        foreign key (department_id) references department;

insert into sum_salary (department_id, department_name) values
    (1, 'Председатель правления'),
    (2, 'Группа развития бизнеса'),
    (3, 'Казначейство'),
    (4, 'Операционное управление'),
    (5, 'Кредитное управление'),
    (6, 'Финансовый контроль и бюджетирование'),
    (7, 'Бухгалтерский учет и отчетность'),
    (8, 'Группа поддержки бизнеса'),
    (9, 'Обслуживание клиентов'),
    (10, 'Валютный контроль'),
    (11, 'Финансовый контроль и аудит'),
    (12, 'Отдел отчетности'),
    (13, 'Юридический отдел');