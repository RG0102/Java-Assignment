/*
Student Name: Ritika Gupta (C21368541)
Date Started: 13th March 2023
Date Ended: 6th April 2023
Program Description: The user puts in a search term into a GUI and the tool will check the contents of a set of text files and tell you which ones contain the search term.
The files that have the “strongest match” against the search term should be returned at the top of the list.
The user should be able to search on a single word. But to make the search better, you should be able to search on multiple words – e.g. “Christmas day”..
although what rules you apply as to whether these are assumed to be together or separate words is up to you; Maybe you can use “*” and logic
(e.g. this word AND this word OR this word) to make the search smarter.
 */

/////////////////////////////////////////////////

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

public class SearchFile18 extends JFrame implements ActionListener
{

    private static int count = 0;
    private static String File;

    public static void main(String args[])
    {
        // Create new JFrame
        JFrame frame = new JFrame();
        frame.setSize(500, 200);
        frame.setLayout(new FlowLayout());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create JPanel
        JPanel searchpanel = new JPanel(new BorderLayout());
        frame.add(searchpanel, BorderLayout.NORTH);

        // Create JLabel
        JLabel label = new JLabel("Search term:");
        searchpanel.add(label, BorderLayout.WEST);
        label.setBackground(Color.red);

        // Create JTextField
        JTextField userText = new JTextField(30);
        searchpanel.add(userText, BorderLayout.CENTER);

        // Create JButton
        JButton button = new JButton("Search");
        searchpanel.add(button, BorderLayout.EAST);
        button.setBounds(50, 20, 50, 20);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setBackground(Color.green);
        button.setForeground(Color.black);
        button.setFont(Font.getFont("Arial"));

        //Create a cancel button
        JButton clearButton = new JButton("Clear");
        clearButton.setHorizontalTextPosition(JButton.CENTER);
        clearButton.setBackground(Color.gray);
        clearButton.setForeground(Color.white);
        clearButton.setFont(Font.getFont("Arial"));
        searchpanel.add(clearButton, BorderLayout.WEST);

        clearButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                if(e.getSource()==clearButton)
                {
                    JOptionPane.showMessageDialog(null, "You have decided to clear the word.");
                    userText.setText(" ");
                }// end if
            }// end void actionPerformed Clear button
        }); // end clearButton,addActionListener
        frame.setVisible(true);

        // Add ActionListener to JButton
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Get search term from JTextField
                String searchTerm = userText.getText().replaceAll("[^a-zA-Z0-9 ]", " ");

                // Choose 3 file to search
                File file = new File("ChristmasGames.txt");
                File file2 = new File("imdb_movie_data.txt");
                File file3 = new File("ChristmasMovies.txt");

                //Add the list of files into the Array List
                List<File> fileList = new ArrayList<>();
                fileList.add(file);
                fileList.add(file2);
                fileList.add(file3);

                List<String> matches = new ArrayList<>();
                Map<Integer, String> matchCountsMap = new HashMap<Integer, String>();

                for(File fileName : fileList)
                {
                    count = 0;
                    extracted(searchTerm, fileName, matches, matchCountsMap);
                }// end for

                TreeMap<Integer,String> sorted = new TreeMap<>(Collections.reverseOrder());

                //Copy all data from hashMap into TreeMap
               sorted.putAll(matchCountsMap);
               String matchResult = "";

                // Display the TreeMap which is naturally sorted
                for (Map.Entry<Integer, String> entry : sorted.entrySet())
                {
                    matchResult = matchResult + entry.getValue()+ ": Total \""+searchTerm+ "\" found = "+entry.getKey() + "times\n";
                }// end inner Mapfor loop 

                JOptionPane.showMessageDialog(null,matchResult);
            }
            
            
            //I have created a extracted method which has SearchTerm, matches, Map and matchCount
            private void extracted(String searchTerm, File file, List<String> matches, Map<Integer, String> matchCountsMap)
            {
                try (BufferedReader br = new BufferedReader(new FileReader(file)))
                {
                    String line = null;
                    int lineNum = 0;
                    while ((line = br.readLine()) != null)
                    {
                        lineNum++;
                        line = line.toLowerCase();
                        //System.out.println(line);
                        StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
                        while (stringTokenizer.hasMoreTokens())
                        {
                            String token = stringTokenizer.nextToken();
                            if (token.contains(searchTerm.toLowerCase()))
                            {
                                count++;
                                matches.add(file.getName() + ", line " + lineNum + ": " + line.trim());
                                break;
                            }// end  inner if

                        }// end inner while loop

                    }// end outer while loop

                    if (matches.size() > 0)
                    {
                        // Display results
                        matchCountsMap.put(count, file.getName());
                    }// end if statement

                }// end try statement

                catch (IOException ex)
                {
                    ex.printStackTrace();
                }// end catch

            }// end void extracted

            private int getMatchStrength(String match)
            {
                int numberwords = match.split("\\s+").length;
                int numberchars = match.length();
                return numberwords * 10 + numberchars;
            }// end get

        });
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
