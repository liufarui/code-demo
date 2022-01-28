# xargs -0表示xargs用NULL来作为分隔符
ls |xargs echo
ls |xargs -0 echo
