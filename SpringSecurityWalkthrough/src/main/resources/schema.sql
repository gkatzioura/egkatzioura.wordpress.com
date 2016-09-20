drop table if exists users;
create table users(id bigint auto_increment, username varchar(255), password varchar(255), enabled boolean);
insert into users(username,password,enabled) values('steve','steve',true);
insert into users(username,password,enabled) values('john','john',true);
drop table if exists authorities;
create table authorities(username  varchar(255),authority  varchar(255), UNIQUE(username,authority));
insert into authorities(username,authority) values('steve','admin');
insert into authorities(username,authority) values('john','superadmin');

drop table if exists Custom_Users;
create table Custom_Users(id bigint auto_increment, username varchar(255), password varchar(255));
insert into Custom_Users(username,password) values('TestUser','TestPass');

drop table if exists Custom_Roles;
create table Custom_Roles(username varchar(255),authority  varchar(255), UNIQUE(username,authority));
insert into Custom_Roles(username,authority) values('TestUser','superadmin');
