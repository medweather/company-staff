create table department (
    id serial,
    name varchar(255) not null,
    from_date timestamp with time zone not null,
    primary key (id)
);

create type gender as enum ('MAN', 'WOMAN');
create type positions as enum ('HEAD', 'FINANCIAL_ANALYST',
    'PERSONAL_FINANCIAL_ADVISOR', 'ACCOUNTANT', 'AUDITORS', 'LOAN_OFFICER',
    'BANK_TELLERS', 'TRADER', 'CHIEF_ACCOUNTANT', 'LEGAL');

create table employee (
    id serial,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    patronymic varchar(255),
    gender gender not null,
    birthday date not null,
    phone varchar(255) not null,
    email varchar(255) not null,
    from_date timestamp with time zone not null,
    dismissal timestamp with time zone,
    position positions not null,
    salary bigint not null,
    head boolean not null default false,
    department_id int4,
    primary key (id)
);

alter table if exists employee
    add constraint employee_department_fk
        foreign key (department_id) references department;