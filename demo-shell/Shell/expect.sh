#!/usr/bin/expect -f
set timeout 30
spawn ssh dtdream@192.168.150.1 -p25556
expect "*password:"
send "DtDream@33\r"
#expect eof
interact