# 判断文件夹是否存在
if [ -d "/data/" ];then
echo "文件夹存在"
else
echo "文件夹不存在"
fi

# 判断文件是否存在
if [ -f "/data/filename" ];then
echo "文件存在"
else
echo "文件不存在"
fi

# 判断file1是否比file2新
if [ "/data/file1" -nt "/data/file2" ]
echo "file1更新"
else
echo "file2更新"
fi
