/*
------------------Java Assignment------------------------
Student Name: Ritika Gupta (C21368541)
Year: TU857/2
Date Started: 13th March 2023
Date Ended: 6th April 2023
Program Description:

The user puts in a search term into a GUI and the tool will check the contents of a set of text files and tell you which ones contain the search term.
The files that have the “strongest match” against the search term should be returned at the top of the list.
The user should be able to search on a single word. But to make the search better, you should be able to search on multiple words – e.g. “Christmas day”..
although what rules you apply as to whether these are assumed to be together or separate words is up to you; Maybe you can use “*” and logic
(e.g. this word AND this word OR this word) to make the search smarter.

OOP Semester - 2 Java and Python

-------------Java Assignment-------------------
 */

//Import Java Libraries
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class SearchFile extends JFrame implements ActionListener
{
    private static int count = 0;
    private static String File;

    public static void main(String args[])
    {
        // Create new JFrame
        JFrame frame = new JFrame();
        frame.setSize(600, 600);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create JPanel
        JPanel searchpanel = new JPanel(new BorderLayout());
        searchpanel.setBackground(Color.red);
        frame.add(searchpanel);

        // Create JLabel
        JLabel label = new JLabel("Search term:");
        searchpanel.add(label, BorderLayout.WEST);

        // Create JTextField
        JTextField userText = new JTextField(30);
        searchpanel.add(userText, BorderLayout.CENTER);
        userText.setEnabled(true);

        // Search JButton
        JButton button = new JButton("Search");
        searchpanel.add(button, BorderLayout.EAST);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setBackground(Color.green);
        button.setForeground(Color.black);
        button.setFont(Font.getFont("Arial"));

        //Clear Button
        JButton clearButton = new JButton("Clear");
        clearButton.setHorizontalTextPosition(JButton.CENTER);
        clearButton.setBackground(Color.gray);
        clearButton.setForeground(Color.white);
        clearButton.setFont(Font.getFont("Arial"));
        searchpanel.add(clearButton, BorderLayout.WEST);

        //Add Action Listener for Clear button when the users click on the clear button
        clearButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                //If statement check if it has clear the word or not.
                if(e.getSource()==clearButton)
                {
                    JOptionPane.showMessageDialog(null, "You have decided to clear the word.");
                    userText.setText(" ");
                }// end if
            }// end void actionPerformed Clear button
        });// end clearButton addActionListener

        frame.setVisible(true);

        // Add ActionListener to JButton
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Get search term from JTextField and it will replace all the special character in my text files
                String searchTerm = userText.getText().replaceAll("[^a-zA-Z0-9 ]", "");

                //It will check the search term and split the term
                String[] words = searchTerm.split(" ");

                // Choose 3 file to search
                File file = new File("ChristmasGames.txt");
                File file2 = new File("imdb_movie_data.txt");
                File file3 = new File("ChristmasMovies.txt");

                //Add the list of files into the Array List
                List<File> fileList = new ArrayList<>();
                fileList.add(file);
                fileList.add(file2);
                fileList.add(file3);

                //List for the matches and I uses the hash map for the wordcountmap
                List<String> matches = new ArrayList<>();
                Map<Integer, String> wordcountmap = new HashMap<Integer, String>();

                    for (File fileName : fileList)
                    {
                        count = 0; // it will reset the count to 0, everytime users enter the word.

                        extracted(searchTerm, fileName, matches, wordcountmap);
                    }// end inner for loop

                    //If statement, check if the user input the wrong word
                    if (wordcountmap.isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "You have mispelled the word wrong. Please try again!!!");

                    }// end if statement

                    //The else loops check the total words in the text files and compare them and it display the output to the user.
                    else
                    {
                        //It reverse thr order in the descending
                        TreeMap<Integer, String> sorted = new TreeMap<>(Collections.reverseOrder());

                        //Copy all data from hashMap into TreeMap
                        sorted.putAll(wordcountmap);
                        String matchResult = "";

                        // Display the TreeMap which is naturally sorted
                        for (Map.Entry<Integer, String> entry : sorted.entrySet())
                        {
                            matchResult = matchResult + entry.getValue() + ": Total \"" + searchTerm + "\" found = " + entry.getKey() + "times\n";

                        }// end inner for loop

                        JOptionPane.showMessageDialog(null, matchResult);

                    }// end else statement

            }// end actionButton performed


            // I have created an extracted method which contains parameter searchTerm, File, matches, Map and wordcountsMap
            private void extracted(String searchTerm, File file, List<String> matches, Map<Integer, String> wordcountmap)
            {
                try (BufferedReader br = new BufferedReader(new FileReader(file)))
                {
                    String line = null;
                    int lineNum = 0;

                    //This while loops read the text files line by line until it reaches the end or null of the text files.
                    while ((line = br.readLine()) != null)
                    {
                        lineNum++;
                        line = line.toLowerCase();

                        //I have  create a string Tokenizer which breaks the Strings into tokens with specified string and delimeter.
                        // It will remove the commas in my text files.
                        StringTokenizer stringTokenizer = new StringTokenizer(line, ",");

                        //The while loop check if the StringTokenizer has more tokens or not.
                        while (stringTokenizer.hasMoreTokens())
                        {
                            //it returns next Token from string Tokenizer Object
                            String token = stringTokenizer.nextToken();
                            if (token.contains(searchTerm.toLowerCase()))
                            {
                                count++;
                                matches.add(file.getName() + ", line " + lineNum + ": " + line.trim());
                                break;
                            }// end if

                        }// end inner while loop

                    }// end outer while loop

                    if (matches.size() > 0)
                    {
                        // Display results
                        wordcountmap.put(count, file.getName());

                    }// end inner if match

                }// end try

                catch (IOException ex)
                {
                    ex.printStackTrace();
                }// end catch

            }// end void extracted

        });// end button Addaction Listener

    }// end void main



    @Override
    public void actionPerformed(ActionEvent e)
    {

    }// end actionPerformed (ActionEvent e)

}// end SearchFile which extends JFrame and implement ActionListener

