# 查看集群命名空间
kubectl get ns
# 查看集群状态
kubectl get cs
# 查看集群节点信息
kubectl get nodes
# 查看指定命名空间的服务
kubectl get svc -n kube-system

# 以yaml格式查看Pod详细信息
kubectl get pod <pod-name> -o yaml

# 查看集群状态信息
kubectl cluster-info 

kubectl describe nodes <node-name>
kubectl describe pods/<pod-name>

# 创建一个资源
kubectl create deployment nginx --image=nginx:1.14 
kubectl create -f my-nginx.yaml
# 可重复
kubectl apply -f my-nginx.yaml

# 在集群中运行一个指定的镜像
kubectl run nginx --image=nginx:1.16 --port=80 --replicas=1

# 扩缩容
kubectl scale deployment nginx --replicas 5

# 创建Service对象以将应用程序"暴露"于网络中
kubectl expose deployment/nginx  --type="NodePort" --port=80 --name=nginx
	
# 设置label
kubectl label nodes controller2-hz20 --overwrite  dtcube/slb-octavia=false

# 编辑配置项
kubectl describe pod *** -n dtcube
kubectl --namespace=dtcube edit cm cinder-volume

