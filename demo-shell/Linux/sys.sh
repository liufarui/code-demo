# 查询磁盘是否为固态
cat /sys/block/sda/queue/rotational

# tcp_timestamps
cat /proc/sys/net/ipv4/tcp_timestamps
/etc/sysctl.conf
net.ipv4.tcp_timestamps = 0
sysctl -p

# 给history加上时间戳
export HISTTIMEFORMAT="%F %T `whoami` "

# 杀死占用yum.pid的进程
rm -f /var/run/yum.pid

# 查看执行时间
time + 命令

# 查看CPU内存信息
/proc/meminfo
/proc/cpuinfo