alter table department add column parent_id int4;

alter table department
    add constraint department_parent_fk
        foreign key (parent_id) references department;