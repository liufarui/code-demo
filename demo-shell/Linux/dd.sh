# dd 复制
# if 源
# of 目的
# bs 块大小
# count 读写块的数量

# --iflag/oflag 
# 	dd做读写测试时，要加两个参数 iflag=nocache 和 oflag=direct 参数。
# 	没有的话dd有时会显示从内存中传输数据的结果，速度会不准确。

# --seek 
# 	seek的作用是跳过输出文件中指定大小的部分，这就达到了创建大文件，但是并不实际写入的目的。
# 	当然，因为不实际写入硬盘，所以你在容量只有10G的硬盘上创建100G的此类文件都是可以的。


# 示例
dd if=/dev/zero of=test bs=1M count=0 seek=100000 oflag=direct

# 纯写速度
time dd if=/dev/zero of=/var/test bs=8k count=1000000 oflag=direct

# 纯读速度
time dd if=/var/test of=/dev/null bs=8k count=1000000 iflag=nocache

# 读写速度
time dd if=/var/test of=/tmp/test bs=8k count=1000000 iflag=nocache

# 将结果重定向到文件
dd if=/dev/zero of=/var/test bs=8k count=1000000  2>> info

dd if=/dev/random of=test bs=1M count=10000 