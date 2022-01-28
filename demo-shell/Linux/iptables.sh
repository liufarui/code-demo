# 发往本机192.168.204.240 IP 的22端口的报文，做DNAT，使其发往201地址的22端口。
iptables -t nat -A PREROUTING -p tcp -d 192.168.204.240 --dport 22 -j DNAT --to-destination 10.20.0.240:22
iptables -t nat -A PREROUTING -p tcp -d 192.168.204.43 -j DNAT --to-destination 10.20.0.22

# 发往201地址22端口的报文，做SNAT，使其源IP变成224本机地址。
iptables -t nat -A POSTROUTING -p tcp -d 10.20.0.201 --dport 22 -j SNAT --to-source 192.168.125.224

# 查看所有的NAT表项
iptables -vnL -t nat --line-number

# 删除对应的NAT表项
iptables -t nat -D POSTROUTING 3

# iptables端口放行
/sbin/iptables -I INPUT -p tcp -s 10.101.2.35 --dport 8080 -j ACCEPT

# iptables端口限速
iptables -A OUTPUT -o deployPort -p tcp -m tcp --sport 9292 -m limit --limit=10000/s --limit-burst=10000
iptables -A OUTPUT -o deployPort -p tcp -m tcp --sport 9292 -j DROP
iptables -A INPUT -i deployPort -p tcp -m tcp --dport 9292 -m limit --limit=10000/s --limit-burst=10000
iptables -A INPUT -i deployPort -p tcp -m tcp --dport 9292 -j DROP

