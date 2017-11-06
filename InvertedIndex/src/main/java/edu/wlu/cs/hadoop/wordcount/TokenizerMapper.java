package edu.wlu.cs.hadoop.wordcount;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * Reads input from the default TextInputFormat.
 * 
 * Maps the words to their count, e.g.,
 * 
 *   &lt; word1, 1 &gt;
 *   &lt; word2, 1 &gt;
 *   ...
 *  
 * @see https://hadoop.apache.org/docs/stable/hadoop-mapreduce-client/hadoop-mapreduce-client-core/MapReduceTutorial.html#Walk-through
 *
 */
public class TokenizerMapper extends Mapper<Object, Text, Text, IntWritable> {

	private final static IntWritable one = new IntWritable(1);
	private Text word = new Text();
	private Normalizer norm = new Normalizer();

	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		StringTokenizer itr = new StringTokenizer(value.toString());
		while (itr.hasMoreTokens()) {
			word.set(itr.nextToken());
			word = norm.normalize(word);
			context.write(word, one);
		}
	}
}