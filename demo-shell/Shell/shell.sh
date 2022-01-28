# 输出回车
#!/bin/bash
echo  $'\n'

# 时间
DATE=`date +"%H%M"

# 循环
for i in {1..10}

# 批量删除文件
#!/bin/bash
DATA=`cat aaa`
for s in ${DATA[@]}
do
rm -rf $s
done