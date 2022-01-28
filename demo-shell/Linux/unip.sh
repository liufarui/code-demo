# file中的重复行输出一次，并在每行前显示重复次数
uniq -c file

# file中的重复行输出一次，但不输出唯一的行
uniq -d file

# 只输出file中的唯一行
uniq -u file

# 把file1中的重复的相邻行删除，并把每行的一个拷贝送到file2
uniq file1 file2
