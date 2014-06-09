package isbd.isbd_join_inter;


import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
//import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.JobClient;
import org.apache.hadoop.mapred.JobConf;
import org.apache.hadoop.mapred.TextInputFormat;
import org.apache.hadoop.mapreduce.Job;
//import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapred.lib.MultipleInputs;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;




/**
 * Hello world!
 *
 */
public class App  extends Configured implements Tool {
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        int exitCode;
		try {
			exitCode = ToolRunner.run(new Configuration(), new App(), args);
			System.exit(exitCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
    }
    
    public int run(String[] args) throws IOException, InterruptedException, ClassNotFoundException {
	
    	String aFile, bFile, output;
    	// aFile  = args[0];
    	// bFile  = args[1];
    	// output = args[2];    	
    	aFile  = "./a.txt"; // "./words.txt"
    	bFile  = "./b.txt";
    	output = "./ab"; // "./out3"
                
        Path inputPath = new Path(aFile);
        Path outputPath = new Path(output);
        Configuration conf = new Configuration();
        Job job = new Job(conf, "word count");
        job.setJarByClass(App.class);
        
        job.setMapperClass(JoinAMapper.class);
        job.setReducerClass(JoinReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
        System.out.println( "job - map & reduce!" );
        
        FileInputFormat.setInputPaths(job, inputPath);
        FileOutputFormat.setOutputPath(job, outputPath);
        job.setNumReduceTasks(1);
        System.out.println( "job - num reduce tasks set!" );
        //job.setPartitionerClass(JoinPartitioner.class);
        job.waitForCompletion(true);
        System.out.println( "job - completed..." );
        //job.submit();
        return 0;
        
	}
    
    
    
    
}
