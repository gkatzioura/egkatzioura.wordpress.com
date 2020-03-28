#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" &amp;lt;&amp;lt;-EOSQL
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
    values ('John','Doe 1','john1@doe.com',18,1234.23);
    insert into spring_data_jpa_example.employee (firstname,lastname,email,age,salary)
    values ('John','Doe 2','john2@doe.com',19,2234.23);
    insert into spring_data_jpa_example.employee (firstname,lastname,email,age,salary)
    values ('John','Doe 3','john3@doe.com',20,3234.23);
    insert into spring_data_jpa_example.employee (firstname,lastname,email,age,salary)
    values ('John','Doe 4','john4@doe.com',21,4234.23);
    insert into spring_data_jpa_example.employee (firstname,lastname,email,age,salary)
    values ('John','Doe 5','john5@doe.com',22,5234.23);
EOSQL