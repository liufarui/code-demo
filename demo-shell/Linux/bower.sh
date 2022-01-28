# 列出安装过的所有包的缓存
bower cache list	

# 清除安装过的所有包的缓存
bower cache clean	

# 离线安装包
bower install <package> --offline	

# 查看指定包名的缓存
bower cache list <package>	

# 查看多个指定包名的缓存，包名之间用空格分隔
bower cache list <package1> <package2>	

# 清除指定包的缓存
bower cache clean <package>	

# 清除指定版本的包的缓存，需要在包名后加#<version>
bower cache clean jquery-ui#1.11.4	
