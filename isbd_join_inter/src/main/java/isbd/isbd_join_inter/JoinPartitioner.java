package isbd.isbd_join_inter;


import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class JoinPartitioner extends Partitioner<Text, IntWritable>{
	@Override
    public int getPartition(Text key, IntWritable value, int numOfPartitions) {
		System.out.println( "partion data..." );
            //if (startsWithVowel(key))
            return 0;
    }
}
