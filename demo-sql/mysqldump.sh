# 备份数据库
# --no-data
# 仅备份数据库结构
# --all-databases
# 所有数据库
mysqldump -h127.0.0.1 -uroot -p123456 --databases <database1 database2> > /opt/127.0.0.1.dump

# 恢复数据库
mysql -h127.0.0.1 -uroot -p123456 <database1> < /opt/127.0.0.1.dump

# 数据库转移
mysqldump -h127.0.0.1 -uroot -p123456 cloud | mysql -h127.0.0.2 -uroot -p123456 cloud
