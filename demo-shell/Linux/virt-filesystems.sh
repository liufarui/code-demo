# Virt-filesystems 
# 加参数-a 检测一个客户机磁盘文件
# 加参数-d检测一个客户机使用的磁盘文件
# 加参数–parts 检测客户机的磁盘分区信息，不包括LVM信息

virt-filesystems -a /home/kvm/guest.img
virt-filesystems -d MyGuestName
virt-filesystems -d MyGuestName --parts