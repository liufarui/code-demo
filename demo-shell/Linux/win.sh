[host]
C:\Windows\System32\drivers\etc\hosts

# win计算md5值
certutil  -hashfile  test.txt  MD5
certutil  -hashfile  test.txt  SHA1
certutil  -hashfile  test.txt  SHA256

# 更新DNS
ipconfig/flushdns

