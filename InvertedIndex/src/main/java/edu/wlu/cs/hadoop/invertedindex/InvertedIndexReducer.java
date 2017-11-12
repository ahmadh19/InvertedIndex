package edu.wlu.cs.hadoop.invertedindex;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * Reducer class for MapReduce process to generate an inverted index of words to the documents
 * that contain them.
 * Takes the intermediate values and reduces them. For each key (the word), gets
 * the iterable "list" of Text values. Concatenates those values, which happen to be
 * file names for the occurrences of the key in documents. 
 * Outputs <key, documents>.
 * 
 * @author Hammad Ahmad
 * 
 */
public class InvertedIndexReducer extends Reducer<Text, Text, Text, Text> {
	
	private Text documents = new Text();

	/**
	 * The reduce method.
	 * 
	 */
	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		
		StringBuilder str = new StringBuilder();
		Set<Text> valuesSet = new TreeSet<Text>();
		
		for(Text text : values) {
			valuesSet.add(text);
		}
		
		for(Text text : valuesSet) {
			if(!text.toString().equals(""))
				str.append(text.toString() + ", ");
		}
		
		documents.set(str.toString());
		
		context.write(key, documents);
	}
}