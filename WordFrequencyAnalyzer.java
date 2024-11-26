package algs31;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WordFrequencyAnalyzer {
	/**********************************************************************************/
	/* You are not allowed to add any fields to this class beyond the one given below */
	/* You may only read in from the file once.  This means you may only use a single */
	/* word reader object.                                                            */
	/**********************************************************************************/
	
	// Maintain a counter for each word in the text.
	SequentialSearchST<String, Integer> counters;
	
	/**
	 * Stores a count of the number of times any word appears in a file.  The file is
	 * read in exactly once at the time time this object is constructed.
	 * 
	 * @param filename the name of the file on which to count all word occurrences.
	 */
	public WordFrequencyAnalyzer(String filename) {
		counters = new SequentialSearchST<>(); //Symbol Table

		try(FileReader file = new FileReader(filename); // Reads the file filename
			BufferedReader br = new BufferedReader(file)){ //More efficiently reads file
			String line; //Create String called line
			while((line = br.readLine())!=null){  // While the next line is not empty
				String words[] = line.toLowerCase().split("[^a-zA-Z]+"); //make lines lowercas and ignore non alphabetic symbols
				for (String word : words){//Now that words have been split up this will loop through
					if(!word.isEmpty()){//Check if word isnt empty
						System.out.println("Processing word: " + word); //Debug
						if (counters.contains(word)){//If the word is already in counters
							counters.put(word, counters.get(word) + 1); //Increment count by 1
						}else{
							counters.put(word,1);// If not add it and set base value to 1
						}
					}
				}
			}
			}
		catch(FileNotFoundException e){//Catches Exception if file does not exist or cant be found
			System.out.println("File not found: " + e.getMessage());
			e.printStackTrace();
		}
		
		catch(IOException e){ //Catches error for any reading erros or problems
			System.out.println("Error reading " +e.getMessage());
			e.printStackTrace();}
		

		//System.out.println(count);
	}
	
	/**
	 * Returns the number of times a given word appears in the file from which this
	 * object was created.
	 * 
	 * @param word the word to count
	 * @return the number of times <code>word</code> appears.
	 */
	public int getCount(String word) {//Receives a string as input
		Integer total = counters.get(word.toLowerCase()); // Make the word lowercase and frequency of word is returned as integer
		if (total == null) {//If the input word is not in table
			return 0;// return 0
		}
		return total;// return frequency total
	}
	
	/**
	 * Returns the maximum frequency over all words in the file from which this
	 * ojbect was created.
	 * 
	 * @return the maximum frequency of any word in the the file.
	 */
	public int maxCount() {
		int highest = 0; //initialize maxCount variable

		for (String word : counters.keys()){ //loop through all "words" in the counters table
			int count = counters.get(word); //retrieves word from table
			if (count > highest){ //compares it to max count and if its bigger
				highest = count;// if it is it is the new highest
			}
		}
		return highest; //return highest
	}
}
