//Student Name: Ritika Gupta (C21368541)
//Date started:
//Date: 02/04/2023
//Program Description: To create a search button

//Importing Files
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

public class SearchFile11 extends JFrame implements ActionListener
{
    private int wordCount = 0;
    private static int count = 0;

    public static void main(String args[])
    {
        // Create new JFrame
        JFrame frame = new JFrame();
        frame.setSize(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create JPanel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.add(panel);

        // Create JLabel
        JLabel label = new JLabel("Search term:");
        label.setBounds(10, 20, 100, 35);
        panel.add(label);

        // Create JTextField
        JTextField userText = new JTextField(30);
        userText.setBounds(100, 20, 165, 25);
        panel.add(userText);

        //JProgress Bar
        JProgressBar progressBar = new JProgressBar();
        progressBar.setBounds(10, 120, 300, 25);
        progressBar.setMinimum(0);
        progressBar.setMaximum(100);
        progressBar.setStringPainted(true);
        panel.add(progressBar);

        // Create JButton
        JButton button = new JButton("Search");
        button.setBounds(10, 80, 80, 25);
        panel.add(button);
        frame.setVisible(true);

        // Add ActionListener to JButton
        button.addActionListener(new ActionListener() {
            public int wordCount = 0;

            @Override
            public void actionPerformed(ActionEvent e)
            {
                wordCount = 0;
                // Get search term from JTextField
                String searchTerm = userText.getText().replaceAll("[^a-zA-Z0-9 ]", "");
                System.out.println("searchTerm=" + searchTerm);

                // Choose 3 file to search
                File file = new File("imdb_movie_data.txt");
                File file2 = new File("ChristmasGames.txt");
                File file3 = new File("ChristmasMovies.txt");

                //Add the list of files into the Array List
                List<File> fileList = new ArrayList<>();
                fileList.add(file);
                fileList.add(file2);
                fileList.add(file3);

                Map<File, Integer> scores = new HashMap<>();
                for(File Filename: fileList)
                {
                      int fileScore = extracted(searchTerm, Filename);
                      scores.put(Filename, fileScore);
                }// end inner for loop

                List<File> sortedFiles = new ArrayList<>(scores.keySet());
                Collections.sort(sortedFiles, new Comparator<File>() {
                    @Override
                    public int compare(File f1, File f2) {
                        return scores.get(f2) - scores.get(f1);
                    }
                });


                //Display the topmatch of the file
                wordCount = 0;
                File topmatch = sortedFiles.get(0);
                int topScore = scores.get(topmatch);

                if (topScore <= 0)
                {
                    JOptionPane.showMessageDialog(null, "\"" + searchTerm + "\" was not found in any  file");
                }

                else
                {
                    JOptionPane.showMessageDialog(null, "\"" + searchTerm + "\" was found" + topScore + "time in" + topmatch.getName() + '.');
                    // wordCount++;
                }

                //wordCount = 0;
                //count = 0;

                JFileChooser fileChooser = new JFileChooser(file);
                fileChooser.setMultiSelectionEnabled(true);

                for(File fileName : fileList )
                {
                    extracted(searchTerm, fileName);
                    int progress = (int) (((double) count / (double) wordCount) * 100);
                    progressBar.setValue(progress);
                }
                //wordCount = 0;

                // Display results
                //If I do wordcount is greater than 0, it works, but it keeps executing the true statement.
                if (wordCount > 0)
                {
                    JOptionPane.showMessageDialog(null, "\"" + searchTerm + "\" was not found in any file.");

                }// end if word count
                else
                {
                    JOptionPane.showMessageDialog(null, "\"" + searchTerm + "\" was found " + count + " times in total.");

                    //JOptionPane.showMessageDialog(null, "\"" + searchTerm + "\" was not found in any file.");

                }// end else word count

            }// end action Performed

            private void extracted(String searchTerm, File file) {

                try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                    String line = null;
                    //Object read = null;
                    //String compare = read.nextLine();

                    while ((line = br.readLine()) != null)
                    {

                        line = line.toLowerCase();
                        System.out.println(line);
                        StringTokenizer stringTokenizer = new StringTokenizer(line,",");
                        while (stringTokenizer.hasMoreTokens())
                        {
                            if(stringTokenizer.nextToken().contains(searchTerm.toLowerCase()))
                            {
                                System.out.println("In matching line:"+ line + "we find the search text " +searchTerm);
                                count++;

                            }// end if
                        }// end inner while loop
                    }// end outer while loop

                    System.out.println("Number of times the search Keywords"+ searchTerm + "was repeated "+ count);
                }// end try
                catch (IOException ex)
                {
                    ex.printStackTrace();
                }// end catch
                //return count;
            }// end void Extracted
            //return wordCount;
        }); //add Action Button.

    }// end main

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
