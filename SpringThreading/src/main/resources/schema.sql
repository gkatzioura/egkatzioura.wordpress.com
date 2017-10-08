create schema spring_data_jpa_example;

USE spring_data_jpa_example;

CREATE TABLE employee (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    email varchar(255) NOT NULL,
    firstname varchar(255) NOT NULL,
    lastname varchar(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY (email)
);


insert into employee (firstname,lastname,email) values ('Emmanouil','Gkatziouras','gkatzioura@gmail.com');
