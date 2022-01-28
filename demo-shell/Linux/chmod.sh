# chmod	
	# 给file的属主增加执行权限
	chmod u+x file	
	# 给file的属主分配读、写、执行(7)的权限，给file的所在组分配读、执行(5)的权限，给其他用户分配 执行(1)的权限
	chmod 751 file 	
	# 上例的另一种形式
	chmod u=rwx,g=rx,o=x file 	
	# 为所有用户分配读权限
	chmod =r file 	
	# 同上例
	chmod 444 file 	
	# 同上例
	chmod a-wx,a+r 	
	# 递归地给directory目录下所有文件和子目录的属主分配读的权限
	chmod -R u+r directory 	
	# 设置用ID，给属主分配读、写和执行权限，给组和其他用户分配读、执行的权限。
	chmod 4755 	
	
# chgrp	
	# 把test的所属组更改root组
	chgrp root test 	
	# 递归地把test目录及该目录下所有文件和子目录的组属性设置成mysql
	chgrp -R mysql test 	
	# 把当前目录中所有文件的组属性设置成root
	chgrp root * 	

# chown
	# 把test文件的属主改进root
	chown root test 	
	# 递归地把test_directory目录下的所有文件属主改成root
	chown -R root test_directory 	
	# 把test_link链接的原文件属主改成root，链接文件属主不变
	chown --dereference root test_link 	
	# 把test_link的链接文件属主改成root，原文件属主不变
	chown --no-dereference root test_link 	
