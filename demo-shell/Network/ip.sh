# add IP Address
ifconfig eno1:2 192.168.204.43 netmask 255.255.255.0

# delete IP Address
ip addr delete 10.10.10.10/24 dev eth0

# up IP
ifconfig eno1:1 192.168.204.40 netmask 255.255.255.0 up

# 获取UUID
nmcli con show

# dhcp获取IP
dhclient

# up/down接口
ip link set XXX up