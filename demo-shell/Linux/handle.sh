# 查看当前各个进程打开的文件句柄数，其结果的第一列表示句柄数，第二列表示进程号
lsof -n|awk '{print $2}'|sort|uniq -c |sort -nr|more 

# 查看相关进程句柄数
lsof |grep tomcat|wc -l

# 查看句柄数量
ulimit -a

# 查看单个进程能够打开的最大文件句柄数量
ulimit -n 

# 调整单进程句柄限制
ulimit -n <最大文件句柄数>

# 系统全局的可用句柄数
more /proc/sys/fs/file-max

# 查看正在使用(分配出去)的所有的句柄数、未使用的所有的句柄数、可使用的最大的句柄数
more /proc/sys/fs/file-nr

