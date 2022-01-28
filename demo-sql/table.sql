-- 建表
CREATE TABLE t1
(
    id   int not null,
    name char(20)
);

CREATE TABLE t1
(
    id   int not null primary key,
    name char(20)
);

CREATE TABLE t1
(
    id   int not null,
    name char(20),
    primary key (id, name)
);

CREATE TABLE t1
(
    id   int not null default 0 primary key,
    name char(20)     default '1'
);

truncate table t1;

-- 复制其中一张表，包括数据
create table newTable as
select *
from oldTable;
-- 根据其中一张表结构创建另一张表
create table newTable as
select *
from oldTable
where true = false
-- 或使用Like进行创建
create table newTable like oldTable;

show
create table （目标表格);
