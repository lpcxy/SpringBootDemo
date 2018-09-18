FLUSH PRIVILEGES;
SET PASSWORD FOR 'root'@'localhost' = PASSWORD('liping');
create database collector;
use collector;
create table t_user(
	id varchar(20) primary key,
	name varchar(50) default null,
	age int default null
);
insert into t_user values("lp001", "liping", 20);