# qcow2
virt-install --connect qemu:///system --name rdsForCenter_old --ram 8192 --vcpus sockets=2,cores=2,threads=2 \
--disk path=rdsForCenter_old.qcow2,format=qcow2,device=disk,bus=virtio \
--network=bridge:br-mng,virtualport_type=openvswitch,model=virtio  \
--network=bridge:br-share,virtualport_type=openvswitch,model=virtio \
--accelerate --vnc --vnclisten=0.0.0.0 --vncport=7001  --os-type=linux --os-variant=rhel7 --noautoconsole --cpu host-passthrough --boot hd

# iso
virt-install --name pxeServer_XXXX --virt-type kvm --vcpus=4 --memory=8096 \
	--os-type=linux \
	--cdrom=/XXX/XXX.iso \
	--disk path=/XXX/XXX.qcow2,bus=virtio,size=100 \
	--network=bridge:br-def,virtualport_type=openvswitch,model=virtio \
    --vnc --vnclisten=0.0.0.0 --clock offset=utc --force --boot hd

# clone
virt-clone --original  kvm --name  cloned_kvm --file /home/data/clone_kvm.qcow

# 查看所有ovs网桥
ovs-vsctl list-br

# 查看所有的tag
ovs-vsctl show

# 查看虚机网口以及对应的tag
virsh dumpxml 17 | grep vnet

# 修改虚机网口的tag
ovs-vsctl set port vnet14 tag=6

# 虚机增加虚拟网络
virsh attach-interface 19 --type bridge --source br-def  --model=virtio --config

# 查看虚机的网络信息
virsh domiflist 19

# 查看网桥上所有的虚拟网口
ovs-vsctl list-ports br-def
