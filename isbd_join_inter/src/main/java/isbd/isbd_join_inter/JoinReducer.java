package isbd.isbd_join_inter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
//import java.util.HashMap;
//import java.util.Map;



import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
//import org.apache.hadoop.mapred.JobConf;
//import org.apache.hadoop.mapred.MapReduceBase;
import org.apache.hadoop.mapred.OutputCollector;
//import org.apache.hadoop.mapred.Reducer;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapred.Reporter;
import org.apache.hadoop.io.LongWritable;


//public class JoinReducer extends MapReduceBase implements Reducer<Text, Text, Text, Text>  {
public class JoinReducer extends Reducer<LongWritable, Text, Text, IntWritable>  {
	private String splitter = ",", aVal="a", bVal="b";
	private ArrayList<String> aList = new ArrayList<String>();
	private ArrayList<String> bList = new ArrayList<String>();
	
	@Override
	//public void reduce(LongWritable key, Iterator<Text> values, Context output) throws IOException, InterruptedException {
	protected void reduce(LongWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
	
		 //while ( values.hasNext() ) {
		for(Text text : values){
	           //String currValue = values.next().toString();
				String currValue = "" + text;
	           String valueSplitted[] = currValue.split(splitter);
	           
	           if(valueSplitted[0].equals(aVal))
	           {
	             aList.add( valueSplitted[2].trim() );
	           }
	           else if(valueSplitted[0].equals(bVal))
	           {
	        	 bList.add( valueSplitted[2].trim() );
	           }
	           else{
	        	   System.out.println("----------should not happen!----------");
	        	   aList.add( valueSplitted[2].trim() );
	        	   bList.add( valueSplitted[2].trim() );
	           }
	      }
	      
		 for( String aStr : aList ){
			 for( String bStr : bList ){
				 String value = aStr + ", " + bStr;
				 //output.collect(new Text(key), new Text(value));
				 //output.collect(new Text(key), new IntWritable(1));
				 context.write(new Text(key+""), new IntWritable(1));
			 }
		 }
	}
}
