package edu.wlu.cs.hadoop.invertedindex;

import java.io.IOException;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

/**
 * Mapper class for MapReduce process to generate an inverted index of words to the documents
 * that contain them.
 * 
 * @author Hammad Ahmad
 * 
 */
public class InvertedIndexMapper extends Mapper<Object, Text, Text, Text> {

	private Text document = new Text();
	private Text word = new Text();
	private Normalizer norm = new Normalizer();

	/**
	 * The map method.
	 * 
	 */
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
		
		Path path = ((FileSplit) context.getInputSplit()).getPath();
		String fileName = path.getName();
		document.set(fileName);
		
		String line = value.toString();
		String words[] = line.split(" "); // split on the space delimiter to get the words in the document
		 
		for(String s : words){ 
			s = norm.normalize(s);
			word.set(s);
			if(!norm.inStopWords(s))
				context.write(word, document);
		}
	}
}