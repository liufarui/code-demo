[timedatectl]

# 读取时间
timedatectl

# 设置时间
timedatectl set-time "YYYY-MM-DD HH:MM:SS"

# 列出所有时区
timedatectl list-timezones

# 设置时区
timedatectl set-timezone Asia/Shanghai

# 是否NTP服务器同步
timedatectl set-ntp yes

# 将硬件时钟调整为与本地时钟一致
timedatectl set-local-rtc 1
or
hwclock --systohc --localtime

# 修改时区为上海时区
cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime
