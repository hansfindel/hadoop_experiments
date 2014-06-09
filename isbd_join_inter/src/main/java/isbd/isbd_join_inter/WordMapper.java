package isbd.isbd_join_inter;


import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

        @Override
        protected void map(LongWritable key, Text value, Context context)
                        throws IOException, InterruptedException {
        		//System.out.println("mapper::key=" + key);
                String line = value.toString();
                //String[] words = line.split("\\W+");
                String[] words = line.split(",");
                //for (String word : words) {
                	//System.out.println("kk");
                //    context.write(new Text(word), new IntWritable(1));
                	//context.write(new Text(word), new Text("k"));
                //}
                String _key = words[0];
                String _val = words[1];
            	int pos = WordCount.hash.indexOf(_val);
            	if(pos < 0){
            		WordCount.hash.add(_val);
            		pos = WordCount.hash.indexOf(_val);
            	}
            	context.write(new Text(_key+""), new IntWritable(pos));
        }

}
