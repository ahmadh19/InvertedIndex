package edu.wlu.cs.hadoop.invertedindex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Normalizes Text by lower-casing and removing punctuation as long as
 * the punctuation removal doesn't change the words's meaning. Also includes
 * a list of stop words to not include in the InvertedIndex.
 * @author cooperbaird
 */
public class Normalizer {

	private HashMap<String, String> meanings;
	private List<String> stopWords;
	
	/**
	 * Initializes meanings with words that lose their meaning when
	 * you remove an apostrophe
	 */
	public Normalizer() {
		meanings = new HashMap<>();
		meanings.put("we're", "we are");
		meanings.put("we'll", "we will");
		meanings.put("he'll", "he will");
		meanings.put("she'll", "she will");
		meanings.put("it's", "it is");
		
		/* Default stopwords list for Lucene and Elasticsearch:
		 * https://github.com/igorbrigadir/stopwords/blob/master/en/lucene_elastisearch.txt
		 */
		stopWords = Arrays.asList("an", "and", "are", "as", "at", 
				"be", "but", "by", "for", "if", "in", 
				"into", "is", "it", "no", "not", "of",
				"on", "or", "such", "that", "the", "their", 
				"then", "there", "these", "they", "this", "to", 
				"was", "will", "with");
	}
	
	/**
	 * @param word the String to normalize
	 * @return the word lower-cased with no punctuation
	 */
	public String normalize(String word) {
		String normalized = word.toLowerCase();
		normalized = meanings.containsKey(normalized) ? meanings.get(normalized) : normalized.replaceAll("\\p{Punct}", "");
		return normalized;
	}
	
	/**
	 * @return whether the stop words list contains word
	 */
	public boolean inStopWords(String word) {
		return stopWords.contains(word);
	}

}
