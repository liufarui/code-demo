# 将下一次启动设置为pxe
ipmitool -I lanplus -H XX.XX.XX.XX -U USER -P SECRET chassis bootdev pxe

# 电源重启
ipmitool -I lanplus -H XX.XX.XX.XX -U USER -P SECRET power reset

# 冷重置
ipmitool -H XX.XX.XX.XX -U USER -P SECRET mc reset cold


# 例子
ipmitool -H 10.158.255.186 -U admin -P admin mc reset cold
ipmitool -I lanplus -H 10.158.255.186 -U admin -P admin chassis bootdev pxe
ipmitool -I lanplus -H 10.158.255.186 -U admin -P admin power reset