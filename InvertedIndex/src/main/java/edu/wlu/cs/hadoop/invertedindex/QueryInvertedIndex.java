package edu.wlu.cs.hadoop.invertedindex;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * A query program that queries the inverted file index. 
 * The program takes as input the directory location of the inverted index (output) files,
 * and a user-specified word (or phrase) as command-line arguments.
 * Returns the IDs of the documents that contain that word.
 * 
 * @author Hammad Ahmad
 *
 */
public class QueryInvertedIndex {

	public static void main(String[] args) {
		
		String fileName = "";
		String queryWord = "";
		StringBuilder returnStr = new StringBuilder();
		Normalizer norm = new Normalizer();
		
		//TODO: update Normalizer.java to use Strings instead, so that the class can be used here to check for stopwords
		
		if(args.length == 2) {
			fileName = args[0];
			queryWord = args[1];
		}
		
		//queryWord = "six";

		Scanner scanner;
		try {
			scanner = new Scanner(new File(fileName));
			while(scanner.hasNextLine()){
				String str = scanner.nextLine();
				// check if the FIRST word in the line is the query word
				// this is because some words, e.g. six, show up in fileNames as well (and can throw off results)
				if(str.indexOf(queryWord) == 0){ 
					System.out.println(str);
					String[] words = str.split("[ \t]");
					for(int i = 1; i <  words.length; i++) { // skip the first word, i.e. the query word
						returnStr.append(words[i] + " ");
					}
				}
			}
			System.out.println(returnStr.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
