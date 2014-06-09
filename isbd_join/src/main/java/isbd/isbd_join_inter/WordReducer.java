package isbd.isbd_join_inter;

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
				
				
                
				//int sum = 0;
				int appearences = 0;
				String output = "";
                for (IntWritable value:values) {
                	String v = WordCount.hash.get(value.get());
                	output += v + "\t";
                        //sum += value.get();
                	appearences++;
                }
                //String output = "k - " + sum + "- asdf";
                if(appearences == 2)
                	context.write(key, new Text(output));
                
			
				//for (Text value:values) {
				//	context.write(key, value);
				//}	
				
        }


}
