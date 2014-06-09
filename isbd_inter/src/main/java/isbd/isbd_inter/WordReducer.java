package isbd.isbd_inter;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.OutputCollector;
import org.apache.hadoop.mapreduce.Reducer;

public class WordReducer extends Reducer<Text, IntWritable, Text, Text> {
	
		@Override
        protected void reduce(Text key, Iterable<IntWritable> values,
        		Context context)
                        throws IOException, InterruptedException {
				
				int appearences = 0;
				for (IntWritable value:values) {
                	appearences++;
                }
                if(appearences == 2)
                	context.write(key, new Text(""));
                
				
        }


}
