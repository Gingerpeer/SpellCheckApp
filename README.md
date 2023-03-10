# SpellCheckApp
SpellCheckApp

SpellCheckApp is a Java program that implements a spell checker using the Java Collection Framework. It uses the HashSet and TreeSet classes to store a dictionary of English words and to suggest possible corrections for misspelled words.

Requirements
Java Development Kit (JDK) version 8 or later
A text editor or integrated development environment (IDE) to edit and run the Java code
Usage
To use the SpellCheckApp, follow these steps:

Download the SpellCheckApp.java file to your local machine.
Open a terminal or command prompt and navigate to the directory where the file is located.
Compile the file using the command javac SpellCheckApp.java.
Run the program using the command java SpellCheckApp.
Follow the prompts to select a file to spell-check.
The program will read in the words.txt file to create a dictionary HashSet, and will then prompt the user to select a file to spell-check. The program will then check each word in the file against the dictionary, outputting any misspelled words and possible corrections.

Customization
If you want to use a different dictionary of words, you can replace the words.txt file with a file of your own. The program expects the file to have one word per line, with no additional characters or formatting.

Credits
The SpellCheckApp was created by Gingerpeer aka Pierre van Zyl.

License
The SpellCheckApp is released under the MIT License. You are free to use, modify, and distribute the program as you see fit, provided that you include a copy of the license in any distribution.