# git打远程tag
	git tag -a "DTCSR_V1.0.2.Beta01" -m "仙居市民卡项目170904"
	git push origin tag DTCSR_V1.0.2.Beta01

# 提交merge
	git fetch
	git rebase     # 全部使用远程分支
	git pull       # 强推 knows changes
	# 提交merge

# 在本地删除远端实际已经不存在的分支
	git fetch --prune

# 略过检查
	git config –global –add http.sslVerify false 

# 从已有的分支创建新的分支,创建一个dev分支
	git checkout -b dev

# 删除tag
	git push origin --delete tag

# 撤销git add
	git reset --mixed

# 清除工作区未跟踪文件
	git clean (-f)

# git密码修改后，修改全局密码
	git config --system --unset credential.helper
	git config --global credential.helper store

# win/linux下对gitlab做免密
	ssh-keygen -t rsa -C "your.email@example.com" -b 4096
	然后将~/.ssh/id_rsa.pub拷贝到GIT  ->  settings  ->  SSH Keys

# 设置多个git免密
	C:\Users\l0626\.ssh\config
	# 配置github.com
	Host github.com                 
	    HostName github.com
	    IdentityFile C:\\Users\\l0626\\.ssh\\id_rsa_github
	    PreferredAuthentications publickey
	    User liufr

	# 配置gitlab.com 
	Host gitlab02.dtdream.com
	    HostName gitlab02.dtdream.com
	    IdentityFile C:\\Users\\l0626\\.ssh\\id_rsa_gitlab
	    PreferredAuthentications publickey
	    User l0626	