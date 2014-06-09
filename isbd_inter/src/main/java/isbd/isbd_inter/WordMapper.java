package isbd.isbd_inter;


import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

        @Override
        protected void map(LongWritable key, Text value, Context context)
                        throws IOException, InterruptedException {
                String line = value.toString();
            	context.write(new Text(line), new IntWritable(1));
        }

}
