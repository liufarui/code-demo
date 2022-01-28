# 查找虚拟机网卡的MAC地址
virsh domiflist vm-node1

# 临时增加网卡的方法，关机后再开机新增网卡配置丢失
virsh attach-interface vm-node1 --type bridge --source br0 --model virtio

# 根据MAC地址删除网卡，即时生效，如果需要最终生效也要使用virsh edit 来修改配置文件
virsh detach-interface vm-node1 --type bridge --mac 52:54:00:84:23:3d --current 

# 创建raw格式并且大小为10G的磁盘
qemu-img  create  -f  raw  /Data/test.raw 10G

# 挂载
virsh attach-disk vm-node1 /Data/test.raw vdb --cache none

# convert
qemu-img convert -p -f raw -O qcow2 test.raw test.qcow2

# CPU动态增加到4个
virsh setvcpus node-test 4 --live

# 查看当前内存大小
virsh qemu-monitor-command node-test --hmp --cmd info balloon

# 设置当前内存为8G
virsh qemu-monitor-command node-test --hmp --cmd balloon 8190