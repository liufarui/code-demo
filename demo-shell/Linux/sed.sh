# $var1 $var2 变量
# test1 old value
# test2 new value
# 
--insert
	# last line
	sed '$atest' test.txt
	sed '$a'"$var1 test" test.txt
	# 匹配追加
	sed '/test1/atest2' test.txt

--delete
	# contains(all)
	sed "/test/d" test.txt

--replace
	# replace(包含test1的一行全部替换, 包含变量)
	sed "/test1/c$var1 test2" test.txt
	# replace(str)
	sed 's/test1/test2/' test.txt
	sed "s/test1/$ntp_server/" test.txt


