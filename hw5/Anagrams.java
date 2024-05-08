package hw5;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {
	final Integer[] primes =  
			{2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 
			31, 37, 41, 43, 47, 53, 59, 61, 67, 
			71, 73, 79, 83, 89, 97, 101};
	Map<Character,Integer> letterTable;
	Map<Long,ArrayList<String>> anagramTable;

	/**
	 * Sets up letterTable to associate each letter with its prime number.
	 */
	public void buildLetterTable() {
		letterTable = new HashMap<Character,Integer>();
		char[] ab = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		for (int i = 0; i < ab.length; i++) {
			letterTable.put(ab[i], primes[i]);
		}
	}


	/**
	 * Creates a new Anagrams object and sets up its instance variables.
	 */
	Anagrams() {
		buildLetterTable();
		anagramTable = new HashMap<Long,ArrayList<String>>();
	}


	/**
	 * Adds a new word to the anagramTable in this Anagrams.
	 * @param s The word to add, a String
	 */
	public void addWord(String s) {
		long key = myHashCode(s);
		ArrayList<String> value = anagramTable.getOrDefault(key, new ArrayList<String>());
		if (value.contains(s)) throw new IllegalStateException("addWord: duplicate value");
		value.add(s);
		anagramTable.put(key, value);
	}
	
	/**
	 * Finds the hashcode for s by the product of the primes associated with its characters.
	 * @param s The String to calculate the hashcode for
	 * @return The hashcode for String s
	 */
	public long myHashCode(String s) {
		if (s=="") throw new IllegalArgumentException();
		char[] sArr = s.toCharArray();
		long result = 1;
		for (int i = 0; i < sArr.length; i++) {
			result *= letterTable.get(sArr[i]);
		}
		return result;
	}
	
	/**
	 * Adds all words from a file named by String s to this Anagrams's anagramTable.
	 * @param s The name of the file to process
	 * @throws IOException
	 */
	public void processFile(String s) throws IOException {
		FileInputStream fstream = new FileInputStream(s);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		while ((strLine = br.readLine()) != null)   {
		  this.addWord(strLine);
		}
		br.close();
	}
	
	/**
	 * Finds the longest sets of anagrams.
	 * @return the longest sets of anagrams. Includes all sets of anagrams with the max length, if more than one.
	 */
	public ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries() {
	    ArrayList<Map.Entry<Long,ArrayList<String>>> output = new ArrayList<Map.Entry<Long,ArrayList<String>>>();
	    int max = 0;
		for (Map.Entry<Long,ArrayList<String>> entry : anagramTable.entrySet()) {
			ArrayList<String> currentValue = entry.getValue();
			if (currentValue.size() > max){
				output.clear();
				max = currentValue.size();
				output.add(entry);
			} else if (currentValue.size() == max) output.add(entry);
		}
		return output;
	}
	
	public static void main(String[] args) {
		//System.out.println(a.letterTable.toString()); 
		//{a=2, b=3, c=5, d=7, e=11, f=13, g=17, h=19, i=23, j=29, k=31, l=37, m=41, n=43, o=47, p=53, q=59, r=61, s=67, t=71, u=73, v=79, w=83, x=89, y=97, z=101}
		//System.out.println(a.myHashCode("alerts")); //236204078
		//System.out.println(a.myHashCode("alters")); //236204078
		//System.out.println(a.myHashCode("alters")==a.myHashCode("alerts")); //true
		
		Anagrams a = new Anagrams();
		
		final long startTime = System.nanoTime();    
		try {
			a.processFile("words_alpha.txt");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		ArrayList<Map.Entry<Long,ArrayList<String>>> maxEntries = a.getMaxEntries();
		final long estimatedTime = System.nanoTime() - startTime;
		final double seconds = ((double) estimatedTime/1000000000);
		System.out.println("Time: "+ seconds);
		System.out.println("List of max anagrams: "+ maxEntries);
	}
}
