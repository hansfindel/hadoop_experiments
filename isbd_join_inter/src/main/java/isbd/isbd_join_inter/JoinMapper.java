package isbd.isbd_join_inter;

import java.io.IOException;



//import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapred.MapReduceBase;
//import org.apache.hadoop.mapred.Mapper;
import org.apache.hadoop.mapred.OutputCollector;
//import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.mapreduce.Mapper;

//public abstract class JoinMapper extends MapReduceBase  implements Mapper<LongWritable, Text, Text, Text> {
public class JoinMapper extends Mapper<LongWritable, Text, LongWritable, Text> {
	protected String id, body, fileTag="-", splitter=",";

	@Override
	public void map(LongWritable key, Text value, Context output) throws IOException, InterruptedException {
		String line = value.toString();
		System.out.println("mapper:line: " + line);
        String splitarray[] = line.split(",");
        id = splitarray[0].trim();
        body = splitarray[1].trim();
       
        //output.collect(new Text(key.toString()), new Text(fileTag+splitter+id+splitter+body));
        //output.collect( key , new Text(fileTag+splitter+id+splitter+body));
        output.write( key , new Text(fileTag+splitter+id+splitter+body));
	}
	//@Override
    //protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
    //        String line = value.toString();
    //        System.out.println("line: " + line);
    //        context.write(new Text(line), new IntWritable(1));        
    //}
}
