package edu.wlu.cs.hadoop.invertedindex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.hadoop.io.Text;

/**
 * Normalizes Text by lowercasing and removing punctuation as long as
 * the punctuation removal doesn't change the Text's meaning. Also includes
 * a list of stopwords to not include in the InvertedIndex.
 * @author cooperbaird
 */
public class Normalizer {

	private HashMap<Text, Text> meanings;
	private List<Text> stopWords;
	
	/**
	 * Initializes meanings with words that lose their meaning when
	 * you remove an apostrophe
	 */
	public Normalizer() {
		meanings = new HashMap<>();
		meanings.put(new Text("we're"), new Text("we are"));
		meanings.put(new Text("we'll"), new Text("we will"));
		meanings.put(new Text("he'll"), new Text("he will"));
		meanings.put(new Text("she'll"), new Text("she will"));
		meanings.put(new Text("it's"), new Text("it is"));
		
		/* Default stopwords list for Lucene and Elasticsearch:
		 * https://github.com/igorbrigadir/stopwords/blob/master/en/lucene_elastisearch.txt
		 */
		stopWords = Arrays.asList(new Text("an"), new Text("and"), new Text("are"), new Text("as"), new Text("at"), 
				new Text("be"), new Text("but"), new Text("by"), new Text("for"), new Text("if"), new Text("in"), 
				new Text("into"), new Text("is"), new Text("it"), new Text("no"), new Text("not"), new Text("of"),
				new Text("on"), new Text("or"), new Text("such"), new Text("that"), new Text("the"), new Text("their"), 
				new Text("then"), new Text("there"), new Text("these"), new Text("they"), new Text("this"), new Text("to"), 
				new Text("was"), new Text("will"), new Text("with"));
	}
	
	/**
	 * @param word the text object to normalize
	 * @return the Text lowercased with no punctuation
	 */
	public Text normalize(Text word) {
		Text normalized = new Text(word.toString().toLowerCase());
		normalized = meanings.containsKey(normalized) ? meanings.get(normalized) : new Text(normalized.toString().replaceAll("\\p{Punct}", ""));
		return normalized;
	}
	
	/**
	 * @return whether the stopwords list contains word
	 */
	public boolean inStopWords(Text word) {
		return stopWords.contains(word);
	}

}
