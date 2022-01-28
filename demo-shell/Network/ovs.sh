# 查看网桥和端口
ovs-vsctl show

# 创建一个网桥
ovs-vsctl add-br br0
ovs-vsctl set bridge br0 datapath_type=netdev

# 添加/删除一个端口
# for system interfaces
ovs-vsctl add-port br0 eth1
ovs-vsctl del-port br0 eth1
# for DPDK
ovs-vsctl add-port br0 dpdk1 -- set interface dpdk1 type=dpdk options:dpdk-devargs=0000:01:00.0
# for DPDK bonds
ovs-vsctl add-bond br0 dpdkbond0 dpdk1 dpdk2 \
    -- set interface dpdk1 type=dpdk options:dpdk-devargs=0000:01:00.0 \
    -- set interface dpdk2 type=dpdk options:dpdk-devargs=0000:02:00.0
# or new version
ovs-vsctl add-port br0 dpdkbond0 \
    -- set interface dpdkbond0 type=dpdk options:dpdk-devargs=0000:01:00.0,0000:02:00.0

# 设置/清除网桥的openflow协议版本
ovs-vsctl set bridge br0 protocols=OpenFlow13
ovs-vsctl clear bridge br0 protocols

# 查看某网桥当前流表
ovs-ofctl dump-flows br0
ovs-ofctl -O OpenFlow13 dump-flows br0
ovs-appctl bridge/dump-flows br0

# 设置/删除控制器
ovs-vsctl set-controller br0 tcp:1.2.3.4:6633
ovs-vsctl del-controller br0

# 查看控制器列表
ovs-vsctl list controller

# 设置/删除被动连接控制器
ovs-vsctl set-manager tcp:1.2.3.4:6640
ovs-vsctl get-manager
ovs-vsctl del-manager

# 设置/移除可选选项
ovs-vsctl set Interface eth0 options:link_speed=1G
ovs-vsctl remove Interface eth0 options link_speed

# 设置fail模式，支持standalone或者secure
# standalone(default)：清除所有控制器下发的流表，ovs自己接管
# secure：按照原来流表继续转发
ovs-vsctl del-fail-mode br0
ovs-vsctl set-fail-mode br0 secure
ovs-vsctl get-fail-mode br0

# 查看接口id
ovs-appctl dpif/show

# 查看接口统计
ovs-ofctl dump-ports br0