# 指定用户执行命令
sudo -H -u cinder bash -c  "mkdir aaa"

# 切换用户
chown tomcat:tomcat manage.war

# 强制注销
sudo pkill Xorg

# 查看所有用户
cat /etc/passwd |cut -f 1 -d :