# add 增加路由
# del 删除路由
# via 网关出口 网关IP地址
# dev 网关出口 物理设备名

# add
ip route add default via 192.168.0.1 dev eth0
ip route add 192.168.0.0/24 via 192.168.0.1 dev eth0
ip route add 192.168.1.1 via 192.168.0.1 dev eth0

# del
ip route del 192.168.0.0/24