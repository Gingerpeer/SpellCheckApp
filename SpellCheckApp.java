/*
* Read in the dictionary
  * Open the "words.txt" file
  * Create a new HashSet<String>
  * Use a Scanner to read each word from the file
  * Convert each word to lowercase and add it to the HashSet
  * Verify that the size of the HashSet is 72875
  * Close the Scanner
* Read in a file to spell-check
  * Prompt the user to select a file using the provided method: getInputFileNameFromUser()
  * Create a new Scanner to read the selected file
  * Set the delimiter to skip over non-letter characters using the provided command: in.useDelimiter("[^a-zA-Z]+");
  * Use a while loop to iterate through the file
  * Convert each word to lowercase and check if it is in the dictionary HashSet
  * If a word is not in the dictionary, output the word and call the corrections() method on the word
Implement the corrections() method
  * The method should take in a misspelled word and a dictionary HashSet
  * Create a new TreeSet<String> to store possible corrections
  * For each possible correction (as specified in the lab instructions), check if it is in the dictionary HashSet and add it to the TreeSet if it is
  * If the TreeSet is empty, return a single element TreeSet containing the message "(no suggestions)"
  * Return the TreeSet of possible corrections
Output the corrections
  * Use a HashSet<String> to keep track of the misspelled words that have been outputted
  * For each misspelled word, call the corrections() method and output the suggested corrections
  * If a correction suggestion is already in the misspelled word HashSet, do not output it again
  * For TreeSet<String> suggestions, use a for-each loop to iterate through and output the suggestions in alphabetical order with no repeats
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.HashMap;

public class SpellCheckApp {
    
    public static TreeSet<String> corrections(String misspelledWord, HashSet<String> dictionary) {

        TreeSet<String> possibleCorrections = new TreeSet<String>();
        
        // Check for missing letters
        for (int i = 0; i <= misspelledWord.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                String possibleWord = misspelledWord.substring(0, i) + c + misspelledWord.substring(i);
                
                if (dictionary.contains(possibleWord)) {
                    possibleCorrections.add(possibleWord);
                }
                
            }
        }
        
        // Check for adjacent letter swaps
        for (int i = 0; i < misspelledWord.length() - 1; i++) {
            String possibleWord = misspelledWord.substring(0, i) + misspelledWord.charAt(i + 1) + misspelledWord.charAt(i) + misspelledWord.substring(i + 2);
            if (dictionary.contains(possibleWord)) {
                possibleCorrections.add(possibleWord);
            }
        }
        
        // Check for letter replacements
        for (int i = 0; i < misspelledWord.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                String possibleWord = misspelledWord.substring(0, i) + c + misspelledWord.substring(i + 1);
                if (dictionary.contains(possibleWord)) {
                    possibleCorrections.add(possibleWord);
                }
            }
        }
        
        // Check for extra letters
        for (int i = 0; i < misspelledWord.length(); i++) {
            String possibleWord = misspelledWord.substring(0, i) + misspelledWord.substring(i + 1);
            if (dictionary.contains(possibleWord)) {
                possibleCorrections.add(possibleWord);
            }
        }
        
        if (possibleCorrections.isEmpty()) {
            possibleCorrections.add("(no suggestions)");
        }
        
        return possibleCorrections;
    }
    


    public static void main(String[] args) {
        HashSet<String> dictionary = new HashSet<String>();
        try {
            Scanner in = new Scanner(new File("dictionary.txt"));
            while (in.hasNext()) {
                dictionary.add(in.next().toLowerCase());
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
        if (dictionary.size() != 72875) {
            System.out.println("Incorrect number of words in dictionary");
            System.exit(0);
        }
        HashSet<String> misspelledWords = new HashSet<String>();
        HashMap<String, TreeSet<String>> wordMap = new HashMap<String, TreeSet<String>>();
        String fileName = getInputFileNameFromUser();
        try {
            Scanner in = new Scanner(new File(fileName));
            in.useDelimiter("[^a-zA-Z]+");
            
            while (in.hasNext()) {
                String word = in.next().toLowerCase();
                if (!dictionary.contains(word)) {
                    if (!misspelledWords.contains(word)) {
                        misspelledWords.add(word);
                        TreeSet<String> suggestions = corrections(word, dictionary);
                        wordMap.put(word, suggestions);
                    }
                    
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
        System.out.println("Herewith The Miss spelled words with their suggestions :");
        System.out.println(wordMap);
    }

    /**
     * Prompts the user to enter a file name and returns the name of the file.
     * 
     * @return the name of the file entered by the user
     */
    public static String getInputFileNameFromUser() {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter the name of the file to spell-check: ");
        String word = console.nextLine();
        console.close();
        return word;
    }
  }
