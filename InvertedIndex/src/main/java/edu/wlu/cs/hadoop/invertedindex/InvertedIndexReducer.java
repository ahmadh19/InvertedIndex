package edu.wlu.cs.hadoop.invertedindex;

import java.io.IOException;
import java.util.Set;
import java.util.TreeSet;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * Takes the intermediate values and reduces them. For each key (the word), gets
 * the iterable "list" of Text values. Concatenates those values, which happen to be
 * file names for the occurrences of the key in documents. 
 * Outputs <key, documents>.
 * 
 */
public class InvertedIndexReducer extends Reducer<Text, Text, Text, Text> {
	
	private Text documents = new Text();

	public void reduce(Text key, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		
		StringBuilder str = new StringBuilder();
		Set<Text> valuesSet = new TreeSet<Text>();
		
		for(Text text : values) {
			valuesSet.add(text);
		}
		
		for(Text text : valuesSet) {
			str.append(text.toString() + ", ");
		}
		
		documents.set(str.toString());
		
		context.write(key, documents);
	}
}