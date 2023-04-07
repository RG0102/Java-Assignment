# Java-Assignment
Hello all, Welcome to my first Java Assignment Search Engine :)

This is a Java program that allows a user to search for specific term with a set of text files. The program has a Graphic User Interface (GUI), that prompts the user to input the search term, which is then used to search for matches in pre-determined, list of text files. The program will then return the files that have the strongest match against the search term at the top of the list.

The program import several Java libraries, including javax.swing, java.awt, java.awt.event, java.io, java.util. It defines a class called SearchFile, which extends the JFrame and implements the ActionListener interface. The class contains several instance, variables including count and File.

The program creates a new JFrame with a size of 600x600 and sets the layout to FlowLayout. It also creates a JPanel with a BorderLayout, which is added to the JFrame. The JPanel has the red background and contains the JLabel that prompt the user to enter a search term, a JTextField where the user can input the search terms and two JButton, one for searching, and one for clearing the input field.

When the user clicks on the clear button, the program clears the text field. When the users clicks on the search button, the program retrieves the search term from the JTextField and searches for it within the predetermined list of text files. The search term is cleaned by removing all special characters and then split it into individual words.

The program then loop through each files in the list and searches for match using the extract method. If the search term matches words in the file, the count is increased. The matches are stored in the List<String>, and the word count is stored in the Map<Integer, String> called wordcountmap. 

If the wordcountmap is empty the program informs the user that the word is misspelled. Otherwise, the program sorts the wordcountmap in descending order and displays the matches to the user using JOptionPane.  

If I got a more time, I would improve my GUI by making a box and makes it looks nicer by adding more colors, I would also add more advance features such as Progress Bar, and choose Files so that user can select the files from the GUI.



