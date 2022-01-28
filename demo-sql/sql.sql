-- 查看连接
show processlist;

-- 查看连接数
show status like '%Threads_connected%';

-- 设置最大连接数
set GLOBAL max_connections=32000;

-- 展示所有连接
show full processlist;

-- 查看超时时间
show variables like  '%timeout%';

-- 数据库编码格式
show variables like "%character%"；

-- 外部访问数据编码格式
show variables like "%collation%";

-- 允许root远程登陆
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '123456' WITH GRANT OPTION;

-- 创建用户并赋权
use user;
create user 'dtcloud'@'%' identified by '123456Lk';
GRANT ALL PRIVILEGES ON *.* TO 'dtcloud'@'%' IDENTIFIED BY '123456' WITH GRANT OPTION;

-- 重载授权表
flush privileges;

-- 刷新host
flush hosts;
