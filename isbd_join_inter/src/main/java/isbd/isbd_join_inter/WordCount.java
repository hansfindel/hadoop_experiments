package isbd.isbd_join_inter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordCount extends Configured implements Tool
{
	public static ArrayList<String> hash = new ArrayList<String>();
    public static void main(String[] args) throws Exception
    {
        int exitCode = ToolRunner.run(new WordCount(), args);
        System.exit(exitCode);
    }

    public int run(String[] args) throws IOException, InterruptedException,
            ClassNotFoundException
    {

        // Path input1Path = new Path(args[0]);
        // Path input2Path = new Path(args[1]);
        // Path outputPath = new Path(args[2]);
        Path input1Path = new Path("a.txt");
        Path input2Path = new Path("b.txt");
        Path outputPath = new Path("./join1");
        
        Configuration conf = new Configuration();
        Job job = new Job(conf, "word join");
        job.setJarByClass(WordCount.class);
        job.setMapperClass(WordMapper.class);
        job.setReducerClass(WordReducer.class);
        
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job, input1Path, input2Path);
        FileOutputFormat.setOutputPath(job, outputPath);
        
        //job.setNumReduceTasks(2);
        //job.setPartitionerClass(VCPartitioner.class);
        
        job.waitForCompletion(true);
        // job.submit();
        return 0;
    }

}
