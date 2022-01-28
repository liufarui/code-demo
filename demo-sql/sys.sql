-- 创建新的远程连接
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '123456' WITH GRANT OPTION;
GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY '' WITH GRANT OPTION;
flush privileges;

set global max_connections = 3600;

-- 设置mysql数据库连接数
vi /etc/my.cnf
[mysqld]max_connections = 3600

-- 修改密码
mysql_secure_installation <Enter> currentpassword for root (enter for none):<Enter>

Set root password?[Y/n] y

New password: 123456

Re-enter new password: 123456

Remove anonymoususers? [Y/n] y

Disallow root loginremotely? [Y/n] n

Remove test databaseand access to it? [Y/n] y

Reload privilegetables now? [Y/n] y

-- 刷新用户
use mysql;
select host, user from user;

-- 因为mysql版本是5.7，因此新建用户为如下命令：
create user docker identified by '123456';

-- 将docker_mysql数据库的权限授权给创建的docker用户，密码为123456：
grant all on docker_mysql.* to docker@'%' identified by '123456' with grant option;

-- 这一条命令一定要有：
flush privileges;
