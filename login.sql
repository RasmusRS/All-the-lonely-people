create database IS_202;

use IS_202;

create table user(
ID integer not null,
mail varchar(30) not null,
u_password varchar(30) not null);

insert into user(ID, mail, u_password)
values(123, 'kris@kris.com', 'hei');
select * from user;

