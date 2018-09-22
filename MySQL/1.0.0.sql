FLUSH PRIVILEGES;
SET PASSWORD FOR 'root'@'localhost' = PASSWORD('liping');
create database collector;
use collector;

drop table if exists t_iam_user;
create table t_iam_user(
	id varchar(64) primary key,
	name varchar(50) not null,
	password int not null,
	index Normal_name (name)
);
insert into t_iam_user values("lp001", "liping", "123456");