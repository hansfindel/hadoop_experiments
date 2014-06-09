#!/bin/sh

MAINCLASS=cl.puc.iic3413.hadoop.WordCount

CLASSPATH=.

for i in `find /user/grima/hadoop-2.4.0/share -name "*.jar"`;
do
  CLASSPATH=$CLASSPATH:$i
done

res1=$(date +%s.%N)
java -cp $CLASSPATH $MAINCLASS hdfs://hercules:9000/user/cruz/README.txt hdfs://hercules:9000/user/cruz/out
res2=$(date +%s.%N)
dt=$(echo "$res2 - $res1" | bc)

echo $dt
