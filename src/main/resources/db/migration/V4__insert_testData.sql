insert into department (name, from_date, parent_id) values
    ('Председатель правления', '2019-01-15', null),
    ('Группа развития бизнеса', '2019-01-17', 1),
    ('Казначейство', '2019-01-17', 1),
    ('Операционное управление', '2019-01-17', 1),
    ('Кредитное управление', '2019-01-18', 1),
    ('Финансовый контроль и бюджетирование', '2019-01-18', 1),
    ('Бухгалтерский учет и отчетность', '2019-01-18', 1),
    ('Группа поддержки бизнеса', '2019-01-19', 1),
    ('Обслуживание клиентов', '2019-01-17', 4),
    ('Валютный контроль', '2019-01-19', 4),
    ('Финансовый контроль и аудит', '2019-01-18', 6),
    ('Отдел отчетности', '2019-01-18', 7),
    ('Юридический отдел', '2019-01-19', 8);

insert into employee (first_name, last_name, patronymic, gender, birthday, phone, email, from_date, position, salary, head, department_id) values
    ('Александр', 'Петров', 'Григорьевич', 'MAN', '1990-10-01', '89543879210', 'pet@mail.ru', '2019-01-15', 'HEAD', 200000, true, 1),
    ('Сергей', 'Войтенко', 'Петрович', 'MAN', '1993-11-24', '89674562391', 'voit@mail.ru', '2019-01-17', 'HEAD', 120000, true, 2),
    ('Ольга', 'Гаврилова', 'Дмитриевна', 'WOMAN', '1989-03-12', '89632349401', 'gavr@gmail.ru', '2019-01-17', 'HEAD', 100000, true, 3),
    ('Анатолий', 'Беглов', 'Сергеевич', 'MAN', '1990-06-04', '89183483245', 'beg@list.ru', '2019-01-17', 'HEAD', 100000, true, 4),
    ('Максим', 'Столяров', 'Валерьевич', 'MAN', '1992-09-30', '89423215674', 'stol@mail.ru', '2019-01-18', 'HEAD', 100000, true, 5),
    ('Валерий', 'Сергиев', 'Алексеевич', 'MAN', '1992-08-02', '89587463492', 'serg@mail.ru', '2019-01-18', 'HEAD', 95000, true, 6),
    ('Елизавета', 'Боярская', 'Михайловна', 'WOMAN', '1986-03-19', '89430238451', 'boar@mail.ru', '2019-01-18', 'HEAD', 90000, true, 7),
    ('Мария', 'Дорошенко', 'Романовна', 'WOMAN', '1995-05-10', '89104592348', 'dor@mail.ru', '2019-01-19', 'HEAD', 95000, true, 8),
    ('Роман', 'Твердохлебов', 'Сергеевич', 'MAN', '1991-07-02', '89473012954', 'tverd@list.ru', '2019-01-17', 'HEAD', 75000, true, 9),
    ('Татьяна', 'Фомина', 'Максимовна', 'WOMAN', '1993-11-01', '89463712917', 'fomina@mail.ru', '2019-01-19', 'HEAD', 75000, true, 10),
    ('Михаил', 'Борисов', 'Леонидович', 'MAN', '1987-04-22', '89610234981', 'boris@mail.ru', '2019-01-18', 'HEAD', 80000, true, 11),
    ('Наталья', 'Юшкова', 'Павловна', 'WOMAN', '1990-08-18', '89105428329', 'ushk@mail.ru', '2019-01-18', 'HEAD', 85000, true, 12),
    ('Павел', 'Громов', 'Романович', 'MAN', '1989-02-09', '89109238471', 'grom@gmail.ru', '2019-01-19', 'HEAD', 95000, true, 13),
    ('Валерий', 'Терехов', 'Борисович', 'MAN', '1995-06-21', '89302918461', 'ter@mail.ru', '2019-02-01', 'FINANCIAL_ANALYST', 55000, false, 11),
    ('Лидия', 'Дмитрова', 'Александровна', 'WOMAN', '1994-12-11', '89302918763', 'dmitr@mail.ru', '2019-02-05', 'BANK_TELLERS', 35000, false, 9),
    ('Григорий', 'Воронов', 'Дмитриевич', 'MAN', '1995-01-23', '89128712091', 'voron@list.ru', '2019-02-15', 'LOAN_OFFICER', 60000, false, 5),
    ('Заир', 'Магомедов', 'Ракитович', 'MAN', '1991-10-13', '89302978390', 'magomed@list.ru', '2019-02-16', 'TRADER', 50000, false, 2);


