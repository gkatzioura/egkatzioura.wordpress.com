create schema spring_data_jpa_example;

create table spring_data_jpa_example.employee(
    id  SERIAL PRIMARY KEY,
    firstname   TEXT    NOT NULL,
    lastname    TEXT    NOT NULL,
    email       TEXT    not null,
    age         INT     NOT NULL,
    salary         real,
    unique(email)
);

insert into spring_data_jpa_example.employee (firstname,lastname,email,age,salary)
values ('Test','Me','test@me.com',18,3000.23);