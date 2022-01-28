#!/bin/bash
flag=false
if [[ $flag == true ]];then
	echo 'Be careful not to fall off!'
fi

#!/bin/bash
flag=false
if $flag;then
	echo 'Be careful not to fall off!'
fi

#!/bin/bash
flag=false
if [ "$flag" = true ] ; then
	echo 'Be careful not to fall off!'
fi

#!/bin/bash
if [ ! $ntp_server ] ;then
  echo ntp_server is undefined
else
  echo ntp_server is $ntp_server
fi