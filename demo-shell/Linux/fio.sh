fio -filename=/root/gdx/test.ext -direct=1 -iodepth 1 -thread -rw=write -ioengine=libaio -bs=10M -size=140G -numjobs=1 -runtime=864000 -group_reporting -name=mytest