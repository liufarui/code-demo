#!/bin/sh
返回值
function test()
{
    echo "arg1 = $1"
    if [ $1 = "1" ] ;then
        echo "19010"
    else
        echo "0"
    fi
}

echo "test 1"
vul=$(test 1)

#!/bin/sh
function test()
{
    echo "arg1 = $1"
    if [ $1 = "1" ] ;then
        return 1
    else
        return 0
    fi
}

echo "test 1"
test 1
echo $?
