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

public class SpellCheckApp {
  // Implement the corrections() method
  // * The method should take in a misspelled word and a dictionary HashSet
  // * Create a new TreeSet<String> to store possible corrections
  // * For each possible correction (as specified in the lab instructions), check if it is in the dictionary HashSet and add it to the TreeSet if it is
  // * If the TreeSet is empty, return a single element TreeSet containing the message "(no suggestions)"
  // * Return the TreeSet of possible corrections
  public static TreeSet<String> corrections(String word, HashSet<String> dictionary) {
    TreeSet<String> suggestions = new TreeSet<String>();
    // Insertion
    for (int i = 0; i <= word.length(); i++) {
        for (char c = 'a'; c <= 'z'; c++) {
            String newWord = word.substring(0, i) + c + word.substring(i);
            if (dictionary.contains(newWord)) {
                suggestions.add(newWord);
            }
        }
    }
    // Deletion
    for (int i = 0; i < word.length(); i++) {
        String newWord = word.substring(0, i) + word.substring(i + 1);
        if (dictionary.contains(newWord)) {
            suggestions.add(newWord);
        }else{
            suggestions.add("(no suggestions)");
        }
      }
     return suggestions;
    }
    // Substitution


    public static void main(String[] args) {
        // Read in the dictionary
        // Open the "words.txt" file
        // Create a new HashSet<String>
        // Use a Scanner to read each word from the file
        // Convert each word to lowercase and add it to the HashSet
        // Verify that the size of the HashSet is 72875
        // Close the Scanner
        HashSet<String> dictionary = new HashSet<String>();
        try {
            Scanner in = new Scanner(new File("words.txt"));
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

        // Read in a file to spell-check
        // Prompt the user to select a file using the provided method: getInputFileNameFromUser()
        // Create a new Scanner to read the selected file
        // Set the delimiter to skip over non-letter characters using the provided command: in.useDelimiter("[^a-zA-Z]+");
        // Use a while loop to iterate through the file
        // Convert each word to lowercase and check if it is in the dictionary HashSet
        // If a word is not in the dictionary, output the word and call the corrections() method on the word
        String fileName = getInputFileNameFromUser();
        try {
            Scanner in = new Scanner(new File(fileName));
            in.useDelimiter("[^a-zA-Z]+");
            HashSet<String> misspelledWords = new HashSet<String>();
            while (in.hasNext()) {
                String word = in.next().toLowerCase();
                if (!dictionary.contains(word)) {
                    if (!misspelledWords.contains(word)) {
                        System.out.println(word);
                        misspelledWords.add(word);
                    }
                    corrections(word, dictionary);
                }
            }
            in.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.exit(0);
        }
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
