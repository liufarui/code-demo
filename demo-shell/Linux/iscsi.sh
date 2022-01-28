# 发现iscsi通道
iscsiadm -m discovery -t sendtarget -p <IP地址>

# 确认已发现iscsi通道
iscsiadm -m node

# 连接iscsi通道
iscsiadm -m node -l

# 查看已连接的通道（session）
iscsiadm -m session
