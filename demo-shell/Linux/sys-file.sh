# 查看所有分区的文件系统类型
blkid

# 挂载sdb
mount -t auto /dev/sdb  /mnt/sdb

# 迭代查看文件占用内存
du --max-depth=1 -h
du -d 1 -h

# 查看文件大小
ls  -lht
du -sh * 

# 杀死文件系统进程
killall nautilus