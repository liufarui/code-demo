# 过滤文件只显示行号
awk '/textstring/ {print FNR}' textfile