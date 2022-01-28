# 字符串包含
str="this is a string"
[[ $str =~ "this" ]] && echo "$str contains this" 
[[ $str == *this* ]] && echo "$str contains this"
[[ $str =~ "that" ]] || echo "$str does NOT contain that"

#!/bin/bash
str="is"
DATA="this1 is a string"
for s in ${DATA[@]}
do
    if [[ $s == $str ]];
        then
        echo out
    fi
done

# 字符串转列表输出
#!/bin/bash
string="hello,shell,haha"  
array=(${string//,/ })  
for var in ${array[@]}
do
   echo $var
done 

#!/bin/bash
string="hello,shell,haha"
OLD_IFS="$IFS"
IFS=","
array=($string)
IFS="$OLD_IFS"
for var in ${array[@]}
do
   echo $var
done

