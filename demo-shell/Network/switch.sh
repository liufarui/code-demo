# 查看所有的pool
display ip pool

# 修改pxe server
# <汇聚>
ip pool vlan6
undo next-server
next-server 10.159.6.248
	
# 对mac绑定IP
# <汇聚>
ip pool vlan6
static-bind ip-address 10.159.6.247 mac-address 9644-5047-b5a5

# 查IP属于哪个MAC
sys
display arp | inc 10.159.6.

# 查看mac地址对应信息
display mac-address | inc 26

# 查看mac地址
display mac-address
display mac-address vlan 221

# 修改vlan
interface GE1/0/45
port trunk pvid vlan 6

# 查看arp
display arp vlan 6 interface GE1/0/31

# delete DNS
undo dns-list XX.XX.XX.XX

# 查看配置
display current-configuration

display this
commit
quit
save
