guestmount -a guest.img -i --rw /mnt/cdisk


# 对于一个在第一个分区包含主要文件系统的windows分区，执行如下命令挂载。
guestmount -a windows.img -m /dev/sda1 --rw /mnt/cdisk
